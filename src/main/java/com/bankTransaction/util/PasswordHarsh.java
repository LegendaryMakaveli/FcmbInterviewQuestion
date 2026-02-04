package com.bankTransaction.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHarsh {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(4));
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
