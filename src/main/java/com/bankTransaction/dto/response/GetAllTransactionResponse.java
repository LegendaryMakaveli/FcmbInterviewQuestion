package com.bankTransaction.dto.response;


import lombok.Data;

@Data
public class GetAllTransactionResponse {
    private String transactionId;
    private String transactionType;
    private String transactionDetails;
    private double remainingBalance;
    private String dataOfTransaction;
}
