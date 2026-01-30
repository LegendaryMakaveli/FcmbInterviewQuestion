package com.bankTransaction.services;

import com.bankTransaction.data.model.Account;
import com.bankTransaction.data.model.Transaction;
import com.bankTransaction.data.model.User;
import com.bankTransaction.data.repositories.UserRepository;
import com.bankTransaction.dto.request.BuyAirtimeRequest;
import com.bankTransaction.dto.request.DoTransfarRequest;
import com.bankTransaction.dto.request.GetTransactionHIstoryRequest;
import com.bankTransaction.dto.response.BuyAirtimeResponse;
import com.bankTransaction.dto.response.DoTransferResponse;
import com.bankTransaction.exception.AccountNotFoundException;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@Service
public class TransactionServiceImplementation implements TransactionServices{
    @Autowired
    private UserRepository userRepository;

    @Override
    public DoTransferResponse transfer(DoTransfarRequest request) {
        boolean user = userRepository.findByAccountNumber(request.getAccountNumber());
        if(!user) throw new AccountNotFoundException("User not found");
        Transaction newTransaction = getTransactionFee(request);
        Account account = new Account();
        User newUser = new User();
        double businessDiscount = 0.27 * newTransaction.getTransactionFees();
        double retailDiscount = 0.18 * newTransaction.getTransactionFees();

        if(request.getAccountNumber().equals(account.getAccountNumber())) throw new AccountNotFoundException("Cannot transfer to the same account");

        return null;
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
        if(request.getAmount().compareTo(new BigDecimal("1000")) <= 0) newTransaction.setTransactionFees(500);
        if(request.getAmount().compareTo(new BigDecimal("10000")) >= 0) newTransaction.setTransactionFees(1000);
        if(request.getAmount().compareTo(new BigDecimal("100000")) >= 0) newTransaction.setTransactionFees(2000);
        if(request.getAmount().compareTo(new BigDecimal("1000000")) >= 0) newTransaction.setTransactionFees(50000);
        return newTransaction;
    }

}
