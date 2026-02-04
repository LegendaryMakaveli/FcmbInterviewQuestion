package com.bankTransaction.exception;

public class UserNotFoundException extends BankTransactionException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
