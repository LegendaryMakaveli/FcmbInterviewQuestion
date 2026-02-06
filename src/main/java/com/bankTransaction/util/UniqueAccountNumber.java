package com.bankTransaction.util;

import com.bankTransaction.data.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@AllArgsConstructor
public class UniqueAccountNumber {
    private final UserRepository userRepository;

    public  String uniqueAccountNumber() {
        String accountNumber;

        do {
            accountNumber = GenerateAccountNumber.generateAccountNumber();
        } while (userRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }
}
