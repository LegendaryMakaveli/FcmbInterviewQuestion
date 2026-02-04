package com.bankTransaction.exception;

public class InvalidAccountException extends BankTransactionException{
    public InvalidAccountException(String message) {
        super(message);
    }
}
