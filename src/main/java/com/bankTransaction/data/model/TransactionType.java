package com.bankTransaction.data.model;

import com.bankTransaction.exception.BankTransactionException;
import com.bankTransaction.exception.InvalidExceptiion;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionType {
    DEPOSIT,
    TRANSFER,
    AIRTIME;

    @JsonCreator
    public static TransactionType fromString(String value) {
        if (value == null) return null;

        try {
            return TransactionType.valueOf(value.toUpperCase().trim());
        } catch (InvalidExceptiion e) {
            throw new BankTransactionException("Invalid gender: " + value +
                    ". Accepted values are: TRANSFER, AIRTIME");
        }
    }
}
