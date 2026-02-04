package com.bankTransaction.util;

import com.bankTransaction.data.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UniqueAccountNumber {
    @Autowired
    private static UserRepository userRepository;

    public static String uniqueAccountNumber() {
        String accountNumber;

        do {
            accountNumber = GenerateAccountNumber.generateAccountNumber();
        } while (userRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }
}
