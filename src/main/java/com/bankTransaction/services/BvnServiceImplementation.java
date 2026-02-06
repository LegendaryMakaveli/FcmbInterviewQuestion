package com.bankTransaction.services;

import com.bankTransaction.data.model.Bvn;
import com.bankTransaction.data.repositories.BvnRepository;
import com.bankTransaction.dto.request.BvnRegistrationRequest;
import com.bankTransaction.dto.response.BvnRegistrationResponse;
import com.bankTransaction.exception.BvnNotFoundException;
import com.bankTransaction.exception.InvalidExceptiion;
import com.bankTransaction.util.GenerateBvn;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bankTransaction.util.Validations.validateBvnRegistrationInput;
import static com.bankTransaction.util.mapper.mapToRegisterBvn;
import static com.bankTransaction.util.mapper.mapToRegisterBvnResponse;


@AllArgsConstructor
@Service
public class BvnServiceImplementation implements BvnService{

    private final BvnRepository bvnRepository;
    private final GenerateBvn generateBvn;

    @Override
    public BvnRegistrationResponse registerBvn(BvnRegistrationRequest request) {
        validateBvnRegistrationInput(request);
        String newBvn = generateBvn.uniqueBvn();
        bvnRepository.findByEmail(request.getEmail()).ifPresent(user -> {throw new InvalidExceptiion("Email already registered with BVN:" + " " + user.getBvn());});
        bvnRepository.findByPhoneNumber(request.getPhoneNumber()).ifPresent(user -> {throw new InvalidExceptiion("Phone number already registered with BVN:" + " " + user.getBvn());});

        Bvn bvn = mapToRegisterBvn(request, newBvn);
        Bvn savedBvn = bvnRepository.save(bvn);

        return mapToRegisterBvnResponse(savedBvn);
    }

    @Override
    public Bvn getBvnData(String bvn){
        return bvnRepository.findByBvn(bvn).orElseThrow(() -> new BvnNotFoundException("BVN not found"));
    }
}
