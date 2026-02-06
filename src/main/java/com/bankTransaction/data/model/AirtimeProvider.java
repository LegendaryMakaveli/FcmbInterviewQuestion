package com.bankTransaction.data.model;

import com.bankTransaction.exception.BankTransactionException;
import com.bankTransaction.exception.InvalidExceptiion;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum AirtimeProvider {
    GLO,
    MTN,
    AIRTEL,
    ETISALAT,
    VODAFONE;

    @JsonCreator
    public static AirtimeProvider fromString(String value) {
        if (value == null) return null;

        try {
            return AirtimeProvider.valueOf(value.toUpperCase().trim());
        } catch (InvalidExceptiion e) {
            throw new BankTransactionException("Invalid gender: " + value +
                    ". Accepted values are: GLO, MTN, AIRTEL, ETISALAT, VODAFONE");
        }
    }
}
