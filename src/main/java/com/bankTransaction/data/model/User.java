package com.bankTransaction.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal balance;
    private AccountType accountType;
    private String password;
    private String accountNumber;
    private int transactionCount;
    private LocalDateTime dateOfCreation;

}
