package com.bankTransaction.dto.request;


import lombok.Data;

@Data
public class BuyAirtimeRequest {
    private String accountNumber;
    private String networkProvider;
    private double amount;
    private String phoneNumber;
}
