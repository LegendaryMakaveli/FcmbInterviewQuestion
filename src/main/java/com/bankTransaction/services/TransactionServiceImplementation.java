package com.bankTransaction.services;


import com.bankTransaction.data.model.Account;
import com.bankTransaction.data.model.AccountType;
import com.bankTransaction.data.model.Transaction;
import com.bankTransaction.data.model.User;
import com.bankTransaction.data.repositories.UserRepository;
import com.bankTransaction.dto.request.BuyAirtimeRequest;
import com.bankTransaction.dto.request.DoTransfarRequest;
import com.bankTransaction.dto.request.GetTransactionHIstoryRequest;
import com.bankTransaction.dto.response.BuyAirtimeResponse;
import com.bankTransaction.dto.response.DoTransferResponse;
import com.bankTransaction.exception.AccountNotFoundException;
import com.bankTransaction.exception.InsufficientBalanceException;
import com.bankTransaction.exception.InvalidAccountException;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import static com.bankTransaction.util.mapper.mapToTransferResponse;



@AllArgsConstructor
@Service
public class TransactionServiceImplementation implements TransactionServices{
    @Autowired
    private UserRepository userRepository;



    @Override
    public DoTransferResponse transfer(DoTransfarRequest request) {
        boolean user = userRepository.findByAccountNumber(request.getAccountNumber());
        if(!user) throw new AccountNotFoundException("Account not found");
        Transaction newTransaction = getTransactionFee(request);
        Account account = new Account();
        User newUser = new User();
        BigDecimal businessDiscount = new BigDecimal(0.27).multiply(newTransaction.getTransactionFees());
        BigDecimal retailDiscount = new BigDecimal(0.18).multiply(newTransaction.getTransactionFees());

        if(request.getAccountNumber().equals(account.getAccountNumber())) throw new InvalidAccountException("Cannot transfer to the same account");
        if(request.getAmount().compareTo(new BigDecimal(String.valueOf(newUser.getBalance()))) > 0) throw new InsufficientBalanceException("Insufficient balance");
        if(newUser.getAccountType().equals(AccountType.BUSINESS) && newUser.getTransactionCount() > 3) {
            BigDecimal newBalance = newUser.getBalance()
                    .subtract(request.getAmount())
                    .subtract(businessDiscount);
            newUser.setBalance(newBalance);
        }else if(newUser.getAccountType().equals(AccountType.RETAIL)){
            BigDecimal newBalance = newUser.getBalance()
                    .subtract(request.getAmount())
                    .subtract(retailDiscount);
            newUser.setBalance(newBalance);
        }
        return mapToTransferResponse();
    }


    @Override
    public BuyAirtimeResponse buyAirtime(BuyAirtimeRequest request) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransaction(GetTransactionHIstoryRequest request) {
        return List.of();
    }






    private static @NonNull Transaction getTransactionFee(DoTransfarRequest request) {
        Transaction newTransaction = new Transaction();
        if(request.getAmount().compareTo(new BigDecimal("1000")) <= 0) newTransaction.setTransactionFees(new BigDecimal(String.valueOf(500)));
        if(request.getAmount().compareTo(new BigDecimal("10000")) >= 0) newTransaction.setTransactionFees(new BigDecimal(String.valueOf(1000)));
        if(request.getAmount().compareTo(new BigDecimal("100000")) >= 0) newTransaction.setTransactionFees(new BigDecimal(String.valueOf(2000)));
        if(request.getAmount().compareTo(new BigDecimal("1000000")) >= 0) newTransaction.setTransactionFees(new BigDecimal(String.valueOf(50000)));
        return newTransaction;
    }

}
