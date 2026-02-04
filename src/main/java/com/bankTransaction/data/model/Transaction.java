package com.bankTransaction.data.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class Transaction {
    private String id;
    private String transactionType;
    private BigDecimal transactionFees;
    private String senderAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private LocalDateTime dateOfTransaction;
}