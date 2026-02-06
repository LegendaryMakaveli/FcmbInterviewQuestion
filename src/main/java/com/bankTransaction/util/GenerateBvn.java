package com.bankTransaction.util;

import com.bankTransaction.data.repositories.BvnRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@AllArgsConstructor
public class GenerateBvn {

    private final BvnRepository bvnRepository;

    private String generateBvn() {
        Random random = new Random();
        StringBuilder bvn = new StringBuilder();
        bvn.append(random.nextInt(9) + 1);
        for(int count = 0; count < 10; count++){
            bvn.append(random.nextInt(10));
        }
        return bvn.toString();
    }

    public String uniqueBvn() {
        String bvn;
        do {
            bvn = generateBvn();
        } while(bvnRepository.findByBvn(bvn).isPresent());
        return bvn;
    }
}