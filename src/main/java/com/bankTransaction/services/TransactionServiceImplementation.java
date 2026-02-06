package com.bankTransaction.services;


import com.bankTransaction.data.model.*;
import com.bankTransaction.data.repositories.TransactionRepository;
import com.bankTransaction.data.repositories.UserRepository;
import com.bankTransaction.dto.request.BuyAirtimeRequest;
import com.bankTransaction.dto.request.DepositRequest;
import com.bankTransaction.dto.request.DoTransfarRequest;
import com.bankTransaction.dto.response.BuyAirtimeResponse;
import com.bankTransaction.dto.response.DepositResponse;
import com.bankTransaction.dto.response.DoTransferResponse;
import com.bankTransaction.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Service
public class TransactionServiceImplementation implements TransactionServices{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public DepositResponse deposit(DepositRequest request) {
        String currentLoginUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(currentLoginUserEmail).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        userRepository.findByAccountNumber(request.getAccountNumber());
        if (!user.getAccountNumber().equals(request.getAccountNumber())) throw new InvalidExceptiion("You can only deposit into your own account!");
        if(request.getAmount().compareTo(BigDecimal.ZERO) <= 0) throw new InvalidExceptiion("Invalid amount");
        BigDecimal amount = user.getBalance().add(request.getAmount());
        user.setTransactionCount(user.getTransactionCount() + 1);
        user.setBalance(amount);
        userRepository.save(user);

        Transaction transaction = Transaction.builder()
                .transactionReference(generateRef())
                .senderAccountNumber(request.getAccountNumber())
                .amount(request.getAmount())
                .transactionType(TransactionType.DEPOSIT)
                .dateOfTransaction(LocalDateTime.now())
                .balance(user.getBalance())
                .status("SUCCESS")
                .build();

        transactionRepository.save(transaction);

        return DepositResponse.builder()
                .message("Deposit Successfully")
                .dateOfTransaction(LocalDateTime.now())
                .build();
    }

    @Override
    @Transactional
    public DoTransferResponse transfer(DoTransfarRequest request) {
        User sender = userRepository.findByAccountNumber(request.getAccountNumber()).orElseThrow(() -> new UserNotFoundException("Sender not found"));
        User receiver = userRepository.findByAccountNumber(request.getDestinationAccountNumber()).orElseThrow(() -> new UserNotFoundException("Receiver not found"));

        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!sender.getEmail().equals(currentUserEmail)) throw new InvalidExceptiion("Unauthorized");

        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).with(LocalTime.MIN);

        long transactionsThisMonth = transactionRepository.countTransactionsThisMonth(sender.getAccountNumber(), startOfMonth);

        BigDecimal baseFeeRate = new BigDecimal("0.0010");
        BigDecimal originalAmount = request.getAmount();
        BigDecimal baseFee = originalAmount.multiply(baseFeeRate);

        BigDecimal discount = calculateDiscount(sender, originalAmount, transactionsThisMonth);
        BigDecimal discountAmount = baseFee.multiply(discount);
        BigDecimal finalFee = baseFee.subtract(discountAmount);
        BigDecimal amountToDeduct = originalAmount.add(finalFee);

        if (sender.getBalance().compareTo(amountToDeduct) < 0) throw new InvalidExceptiion("Insufficient funds for transfer + fees");

        sender.setBalance(sender.getBalance().subtract(amountToDeduct));
        receiver.setBalance(receiver.getBalance().add(originalAmount));

        userRepository.save(sender);
        userRepository.save(receiver);

        saveTransferRecord(sender, receiver, originalAmount, discount);

        return DoTransferResponse.builder()
                .message("Transfer Successful")
                .dateOfTransaction(LocalDateTime.now())
                .build();
    }

    @Override
    @Transactional
    public BuyAirtimeResponse buyAirtime(BuyAirtimeRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (user.getBalance().compareTo(request.getAmount()) < 0) throw new InvalidExceptiion("Insufficient balance to buy airtime");

        user.setBalance(user.getBalance().subtract(request.getAmount()));
        userRepository.save(user);

        Transaction transaction = Transaction.builder()
                .transactionReference(generateRef())
                .senderAccountNumber(user.getAccountNumber())
                .destinationAccountNumber(request.getPhoneNumber())
                .amount(request.getAmount())
                .transactionType(TransactionType.AIRTIME)
                .dateOfTransaction(LocalDateTime.now())
                .status("SUCCESS")
                .description("You purchase: " + request.getNetworkProvider() +  " " + "airtime" + " to " + request.getPhoneNumber())
                .build();

        transactionRepository.save(transaction);

        return BuyAirtimeResponse.builder()
                .message("Airtime purchase of " + request.getAmount() + " successful")
                .reference(transaction.getTransactionReference())
                .build();
    }


    @Override
    public Page<Transaction> getStatement(String accountNumber, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize,
                Sort.by("dateOfTransaction").descending());

        return transactionRepository.findAllByAccountNumber(accountNumber, pageable);
    }

    public List<Map<String, Object>> getDashboardRecentHistory(String accountNumber) {
        Pageable topFive = PageRequest.of(0, 5, Sort.by("dateOfTransaction").descending());
        List<Transaction> transactions = transactionRepository.findAllByAccountNumber(accountNumber, topFive).getContent();

        return transactions.stream().map(txn -> {
            Map<String, Object> map = new HashMap<>();
            boolean isDebit = txn.getSenderAccountNumber().equals(accountNumber);

            map.put("reference", txn.getTransactionReference());
            map.put("type", txn.getTransactionType());
            map.put("amount", isDebit ? "-" + txn.getAmount() : "+" + txn.getAmount());
            map.put("date", txn.getDateOfTransaction().toLocalDate().toString());
            map.put("status", txn.getStatus());
            map.put("direction", isDebit ? "SENT" : "RECEIVED");

            return map;
        }).toList();
    }






    private String generateRef() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int randomDigits = (int)(Math.random() * 9000) + 1000; // 4 random digits
        return "BT-" + timestamp + randomDigits;
    }

    private void saveTransferRecord(User sender, User receiver, BigDecimal amount, BigDecimal discount) {
        Transaction transaction = Transaction.builder()
                .transactionReference(generateRef())
                .senderAccountNumber(sender.getAccountNumber())
                .destinationAccountNumber(receiver.getAccountNumber())
                .amount(amount)
                .transactionType(TransactionType.TRANSFER)
                .dateOfTransaction(LocalDateTime.now())
                .status("SUCCESS")
                .description("Transfer with discount of: " + discount)
                .build();

        transactionRepository.save(transaction);
    }

    private BigDecimal calculateDiscount(User user, BigDecimal amount, long txCount) {
        long yearsAsCustomer = ChronoUnit.YEARS.between(user.getDateOfCreation(), LocalDateTime.now());

        if (yearsAsCustomer >= 4 && txCount < 3) {
            return new BigDecimal("0.10");
        }

        if (user.getAccountType() == AccountType.BUSINESS && txCount >= 3 && amount.compareTo(new BigDecimal("150000")) > 0) {
            return new BigDecimal("0.27");
        }

        if (user.getAccountType() == AccountType.RETAIL && txCount >= 3 && amount.compareTo(new BigDecimal("50000")) > 0) {
            return new BigDecimal("0.18");
        }

        return BigDecimal.ZERO;
    }
}
