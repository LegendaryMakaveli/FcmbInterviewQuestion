package com.bankTransaction.controllers;

import com.bankTransaction.dto.request.BvnRegistrationRequest;
import com.bankTransaction.dto.response.ApiResponses;
import com.bankTransaction.exception.BankTransactionException;
import com.bankTransaction.exception.InvalidExceptiion;
import com.bankTransaction.services.BvnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bvn")
public class BvnController {
    @Autowired
    private BvnService bvnService;

    @PostMapping("/registerforbvn")
    public ResponseEntity<?> registerForBvn(BvnRegistrationRequest request){
        try{
            return new ResponseEntity<>(new ApiResponses(true, bvnService.registerBvn(request)), HttpStatus.CREATED);
        }catch (InvalidExceptiion error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (BankTransactionException error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
