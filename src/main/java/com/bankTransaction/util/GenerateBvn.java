package com.bankTransaction.util;

import com.bankTransaction.data.repositories.BvnRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@AllArgsConstructor
@Service
public class GenerateBvn {
    @Autowired
    private static BvnRepository bvnRepository;

    public static String generateBvn(){
        Random random = new Random();
        StringBuilder bvn = new StringBuilder();
        bvn.append(random.nextInt(9) + 1);

        for(int count = 0; count < 10; count++){
            bvn.append(random.nextInt(10));
        }

        return bvn.toString();
    }

    public static String uniqueBvn(){
        String bvn;
        do{
            bvn = generateBvn();
        }while(bvnRepository.findByBvn(bvn).isPresent());
        return bvn;
    }
}
