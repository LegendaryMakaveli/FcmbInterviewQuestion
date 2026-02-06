package com.bankTransaction.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class DepositRequest {
    private String firstName;
    private String lastName;

    @NotBlank(message = "Account number is required")
    private String AccountNumber;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal Amount;

    private String PhoneNumber;
}
