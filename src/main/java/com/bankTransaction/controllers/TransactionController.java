package com.bankTransaction.controllers;

import com.bankTransaction.dto.request.BuyAirtimeRequest;
import com.bankTransaction.dto.request.DepositRequest;
import com.bankTransaction.dto.request.DoTransfarRequest;
import com.bankTransaction.dto.response.ApiResponses;
import com.bankTransaction.exception.AccountNotFoundException;
import com.bankTransaction.exception.BankTransactionException;
import com.bankTransaction.exception.InvalidExceptiion;
import com.bankTransaction.exception.UserNotFoundException;
import com.bankTransaction.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionServices transactionServices;

    @PostMapping("/deposittoyouraccount")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest request){
        try{
            return new ResponseEntity<>(new ApiResponses(true,transactionServices.deposit(request)), HttpStatus.OK);
        }catch (InvalidExceptiion | AccountNotFoundException | UserNotFoundException error) {
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (BankTransactionException error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transfertoyourfriend")
    public ResponseEntity<?> transfer(@RequestBody DoTransfarRequest request){
        try{
            return new ResponseEntity<>(new ApiResponses(true,transactionServices.transfer(request)), HttpStatus.OK);
        }catch (InvalidExceptiion | AccountNotFoundException | UserNotFoundException error) {
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (BankTransactionException error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/younurgolikebuyairtime")
    public ResponseEntity<?> buyAirtime(@RequestBody BuyAirtimeRequest request){
        try{
            return new ResponseEntity<>(new ApiResponses(true,transactionServices.buyAirtime(request)), HttpStatus.OK);
        }catch (InvalidExceptiion | AccountNotFoundException | UserNotFoundException error) {
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (BankTransactionException error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getyourtransactionstatement")
    public ResponseEntity<?> getStatement(@RequestParam String accountNumber, @RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            return new ResponseEntity<>(new ApiResponses(true, transactionServices.getStatement(accountNumber, pageNumber, pageSize)), HttpStatus.OK);
        } catch (InvalidExceptiion | AccountNotFoundException | UserNotFoundException error) {
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (BankTransactionException error) {
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getrecenttransaction")
    public ResponseEntity<?> recentHistory(@RequestParam String accountNumber) {
        try{
            return new ResponseEntity<>(new ApiResponses(true, transactionServices.getDashboardRecentHistory(accountNumber)), HttpStatus.OK);
        }catch (InvalidExceptiion | AccountNotFoundException | UserNotFoundException error) {
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (BankTransactionException error){
            return new ResponseEntity<>(new ApiResponses(false, error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
