package com.bankTransaction.dto.request;


import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String newPassword;
}
