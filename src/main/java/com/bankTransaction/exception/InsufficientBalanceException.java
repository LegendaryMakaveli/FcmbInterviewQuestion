package com.bankTransaction.exception;

public class InsufficientBalanceException extends BankTransactionException{
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
