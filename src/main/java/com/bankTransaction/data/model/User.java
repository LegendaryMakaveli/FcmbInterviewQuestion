package com.bankTransaction.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Data
public class User {
    @Id
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String accountNumber;
    private int transactionCount;
    private LocalDateTime dateOfCreation;

}
