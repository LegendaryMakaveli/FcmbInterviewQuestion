package com.bankTransaction.data.model;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Transaction {
    private String id;
    private String transactionType;
    private double transactionFees;
    private String senderAccountNumber;
    private String destinationAccountNumber;
    private double amount;
    private LocalDateTime dateOfTransaction;
}