package com.bankTransaction.exception;

public class AccountNotFoundException extends BankTransactionException{
    public AccountNotFoundException(String message) {
        super(message);
    }
}
