package com.bankTransaction.dto.response;

import com.bankTransaction.data.model.AccountType;
import lombok.Data;


@Data
public class RegisterUserResponse {
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;
    private AccountType accountType;
    private String accountNumber;
    private String message;
    private String dateOfRegistration;
}
