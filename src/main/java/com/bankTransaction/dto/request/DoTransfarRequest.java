package com.bankTransaction.dto.request;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class DoTransfarRequest {
    private String accountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private int pin;
}
