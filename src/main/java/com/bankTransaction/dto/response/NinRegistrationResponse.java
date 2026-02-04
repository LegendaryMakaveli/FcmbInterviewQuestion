package com.bankTransaction.dto.response;


import lombok.Data;

@Data
public class NinRegistrationResponse {
    private String nin;
    private String firstName;
    private String lastName;
    private String message;
}
