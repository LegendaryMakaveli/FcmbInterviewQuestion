package com.bankTransaction.util;

import java.util.Random;

public class GenerateAccountNumber {
    private static final Random random = new Random();

    public static String generateAccountNumber() {
        StringBuilder accountNumber =  new StringBuilder("14");

        for(int count = 0; count < 8; count++){
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
}
