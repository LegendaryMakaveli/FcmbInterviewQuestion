package com.bankTransaction.util;

import lombok.AllArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
public class Token {
    public static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

