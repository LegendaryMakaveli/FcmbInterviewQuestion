package com.bankTransaction.util;

import com.bankTransaction.data.repositories.NinRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class GenerateNin {
    @Autowired
    private static NinRepository ninRepository;

    public static String generateNin() {
        return GenerateBvn.generateBvn();
    }

    public static String uniqueNin(){
        String nin;
        do {
            nin = generateNin();
        } while (ninRepository.findByNin(nin).isPresent());
        return nin;
    }
}
