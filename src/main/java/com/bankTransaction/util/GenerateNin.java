package com.bankTransaction.util;

import com.bankTransaction.data.repositories.NinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Random;


@Component
@AllArgsConstructor
public class GenerateNin {
    private final NinRepository ninRepository;

    private String generateNin() {
        Random random = new Random();
        StringBuilder bvn = new StringBuilder();
        bvn.append(random.nextInt(9) + 1);
        for(int count = 0; count < 10; count++){
            bvn.append(random.nextInt(10));
        }
        return bvn.toString();
    }


    public String uniqueNin(){
        String nin;
        do {
            nin = generateNin();
        } while (ninRepository.findByNin(nin).isPresent());
        return nin;
    }
}
