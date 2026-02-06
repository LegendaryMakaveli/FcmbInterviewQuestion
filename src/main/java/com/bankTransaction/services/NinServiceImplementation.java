package com.bankTransaction.services;

import com.bankTransaction.data.model.Bvn;
import com.bankTransaction.data.model.Nin;
import com.bankTransaction.data.repositories.NinRepository;
import com.bankTransaction.dto.request.NinRegistrationRequest;
import com.bankTransaction.dto.response.NinRegistrationResponse;
import com.bankTransaction.exception.InvalidExceptiion;
import com.bankTransaction.exception.NinNotFoundException;
import com.bankTransaction.util.GenerateNin;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bankTransaction.util.Validations.validateNinRegistrationInput;
import static com.bankTransaction.util.mapper.mapToRegisterNinRequest;
import static com.bankTransaction.util.mapper.mapToRegisterNinResponse;


@AllArgsConstructor
@Service
public class NinServiceImplementation implements NinService{

    private final NinRepository ninRepository;
    private final GenerateNin generateNin;

    @Override
    public NinRegistrationResponse registerNin(NinRegistrationRequest request) {
        validateNinRegistrationInput(request);
        String newNin = generateNin.uniqueNin();
        ninRepository.findByEmail(request.getEmail()).ifPresent(user -> {throw new InvalidExceptiion("Email already registered with NIN:" + " " + user.getNin());});
        Nin nin = mapToRegisterNinRequest(request, newNin);
        Nin savedNin = ninRepository.save(nin);

        return mapToRegisterNinResponse(savedNin);
    }

    @Override
    public Nin getNinData(String nin) {
        if(nin == null || nin.trim().isEmpty())throw new InvalidExceptiion("NIN cannot be empty");
        return ninRepository.findByNin(nin).orElseThrow(() -> new NinNotFoundException("NIN not found"));
    }

    @Override
    public boolean verifyNinMatchesBvn(String nin, Bvn bvn) {
        if(nin == null || nin.trim().isEmpty())throw new InvalidExceptiion("NIN cannot be empty");
        if(bvn == null)throw new InvalidExceptiion("BVN cannot be empty");
        Nin ninRecord = getNinData(nin);

        return ninRecord.getFirstName().equalsIgnoreCase(bvn.getFirstName()) &&
                ninRecord.getLastName().equalsIgnoreCase(bvn.getLastName()) &&
                ninRecord.getDateOfBirth().equals(bvn.getDateOfBirth());
    }
}
