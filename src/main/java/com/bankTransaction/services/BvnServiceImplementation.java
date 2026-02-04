package com.bankTransaction.services;

import com.bankTransaction.data.model.Bvn;
import com.bankTransaction.data.repositories.BvnRepository;
import com.bankTransaction.dto.request.BvnRegistrationRequest;
import com.bankTransaction.dto.response.BvnRegistrationResponse;
import com.bankTransaction.exception.BvnNotFoundException;
import com.bankTransaction.exception.InvalidExceptiion;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bankTransaction.util.mapper.mapToRegisterBvn;
import static com.bankTransaction.util.mapper.mapToRegisterBvnResponse;


@AllArgsConstructor
@Service
public class BvnServiceImplementation implements BvnService{
    @Autowired
    private BvnRepository bvnRepository;

    @Override
    public BvnRegistrationResponse registerBvn(BvnRegistrationRequest request) {
        bvnRepository.findByEmail(request.getEmail()).ifPresent(user -> {throw new InvalidExceptiion("Email already registered with BVN:" + " " + user.getBvn());});
        bvnRepository.findByPhoneNumber(request.getPhoneNumber()).ifPresent(user -> {throw new InvalidExceptiion("Phone number already registered with BVN:" + " " + user.getBvn());});

        Bvn bvn = mapToRegisterBvn(request);
        Bvn savedBvn = bvnRepository.save(bvn);

        return mapToRegisterBvnResponse(savedBvn);
    }

    @Override
    public Bvn getBvnData(String bvn){
        return bvnRepository.findByBvn(bvn).orElseThrow(() -> new BvnNotFoundException("BVN not found"));
    }
}
