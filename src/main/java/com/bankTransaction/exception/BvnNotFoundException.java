package com.bankTransaction.exception;

public class BvnNotFoundException extends BankTransactionException {
    public BvnNotFoundException(String message) {
        super(message);
    }
}
