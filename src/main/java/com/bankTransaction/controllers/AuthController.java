package com.bankTransaction.controllers;


import com.bankTransaction.dto.request.ChangePasswordRequest;
import com.bankTransaction.dto.request.LoginUserRequest;
import com.bankTransaction.dto.request.RegisterUserRequest;
import com.bankTransaction.dto.request.ResetPasswordRequest;
import com.bankTransaction.dto.response.ApiResponses;
import com.bankTransaction.exception.BankTransactionException;
import com.bankTransaction.exception.InvalidExceptiion;
import com.bankTransaction.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest request){
        try{
            return new ResponseEntity<>(new ApiResponses(true, authService.registerUser(request)), HttpStatus.CREATED);
        }catch (InvalidExceptiion error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (BankTransactionException error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/thisislogin")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request){
        try{
            return new ResponseEntity<>(new ApiResponses(true, authService.login(request)), HttpStatus.OK);
        }catch (InvalidExceptiion error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (BankTransactionException error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/yourcanchangepassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request){
        try{
            return new ResponseEntity<>(new ApiResponses(true, authService.changePassword(request)), HttpStatus.OK);
        }catch (InvalidExceptiion error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (BankTransactionException error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/youcanreset")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request){
        try{
            return new ResponseEntity<>(new ApiResponses(true, authService.resetPassword(request)), HttpStatus.OK);
        }catch (InvalidExceptiion error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (BankTransactionException error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getuserprofile")
    public ResponseEntity<?> getUserProfile(@RequestParam String accountNumber){
        try{
            return new ResponseEntity<>(new ApiResponses(true, authService.getUserProfile(accountNumber)), HttpStatus.OK);
        }catch (InvalidExceptiion error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (BankTransactionException error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
