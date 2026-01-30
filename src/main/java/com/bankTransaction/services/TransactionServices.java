package com.bankTransaction.services;

import com.bankTransaction.data.model.Transaction;
import com.bankTransaction.dto.request.BuyAirtimeRequest;
import com.bankTransaction.dto.request.DoTransfarRequest;
import com.bankTransaction.dto.request.GetTransactionHIstoryRequest;
import com.bankTransaction.dto.response.BuyAirtimeResponse;
import com.bankTransaction.dto.response.DoTransferResponse;

import java.util.List;

public interface TransactionServices {
    DoTransferResponse transfer(DoTransfarRequest request);
    BuyAirtimeResponse buyAirtime(BuyAirtimeRequest request);
    List<Transaction> getAllTransaction(GetTransactionHIstoryRequest request);
}
