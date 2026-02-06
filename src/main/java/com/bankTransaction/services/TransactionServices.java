package com.bankTransaction.services;

import com.bankTransaction.data.model.Transaction;
import com.bankTransaction.dto.request.BuyAirtimeRequest;
import com.bankTransaction.dto.request.DepositRequest;
import com.bankTransaction.dto.request.DoTransfarRequest;
import com.bankTransaction.dto.response.BuyAirtimeResponse;
import com.bankTransaction.dto.response.DepositResponse;
import com.bankTransaction.dto.response.DoTransferResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface TransactionServices {
    DepositResponse deposit(DepositRequest request);
    DoTransferResponse transfer(DoTransfarRequest request);
    BuyAirtimeResponse buyAirtime(BuyAirtimeRequest request);
    Page<Transaction> getStatement(String accountNumber, int pageNumber, int pageSize);
    List<Map<String, Object>> getDashboardRecentHistory(String accountNumber);
}
