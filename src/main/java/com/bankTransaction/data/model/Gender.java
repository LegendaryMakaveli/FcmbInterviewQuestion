package com.bankTransaction.data.model;

import com.bankTransaction.exception.BankTransactionException;
import com.bankTransaction.exception.InvalidExceptiion;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    MALE,
    FEMALE,
    CONFUSED;

    @JsonCreator
    public static Gender fromString(String value) {
        if (value == null) return null;

        try {
            return Gender.valueOf(value.toUpperCase().trim());
        } catch (InvalidExceptiion e) {
            throw new BankTransactionException("Invalid gender: " + value +
                    ". Accepted values are: MALE, FEMALE, CONFUSED");
        }
    }
}
