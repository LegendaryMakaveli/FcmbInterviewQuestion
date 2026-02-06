package com.bankTransaction.controllers;


import com.bankTransaction.dto.request.NinRegistrationRequest;
import com.bankTransaction.dto.response.ApiResponses;
import com.bankTransaction.exception.BankTransactionException;
import com.bankTransaction.exception.InvalidExceptiion;
import com.bankTransaction.services.NinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nin")
public class NinController {
    @Autowired
    private NinService ninService;

    @PostMapping("/registerfornin")
    public ResponseEntity<?> registerForNin(@RequestBody NinRegistrationRequest request){
        try{
            return new ResponseEntity<>(new ApiResponses(true, ninService.registerNin(request)), HttpStatus.CREATED);
        }catch (InvalidExceptiion error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (BankTransactionException error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
