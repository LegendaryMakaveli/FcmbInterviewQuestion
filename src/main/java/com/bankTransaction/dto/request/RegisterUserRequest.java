package com.bankTransaction.dto.request;

import com.bankTransaction.data.model.AccountType;
import lombok.Data;


@Data
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private AccountType accountType;
    private String password;
}
