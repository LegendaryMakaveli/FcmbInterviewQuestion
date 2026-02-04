package com.bankTransaction.dto.response;


import lombok.Data;

@Data
public class BvnRegistrationResponse {
    private String bvn;
    private String firstName;
    private String lastName;
    private String message;
}
