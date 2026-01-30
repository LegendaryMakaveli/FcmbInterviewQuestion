package com.bankTransaction.dto.request;


import lombok.Data;

@Data
public class DoTransfarRequest {
    private String accountNumber;
    private String destinationAccountNumber;
    private double amount;
}
