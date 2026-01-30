package com.bankTransaction.exception;

public class UserAlreadyExistException extends BankTransactionException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
