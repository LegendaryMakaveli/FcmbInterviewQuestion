package com.bankTransaction.data.model;

import com.bankTransaction.exception.BankTransactionException;
import com.bankTransaction.exception.InvalidExceptiion;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum AccountType {
    BUSINESS,
    RETAIL,
    STUDENT;

    @JsonCreator
    public static AccountType fromString(String value) {
        if (value == null) return null;

        try {
            return AccountType.valueOf(value.toUpperCase().trim());
        } catch (InvalidExceptiion e) {
            throw new BankTransactionException("Invalid Account-type: " + value +
                    ". Accepted values are: BUSINESS, RETAIL, STUDENT");
        }
    }
}
