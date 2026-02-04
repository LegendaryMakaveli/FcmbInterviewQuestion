package com.bankTransaction.dto.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginUserResponse {
    private String token;
    private String message;
    private String email;
    private String dateOfLogin;
    private String role;
    private Long userId;
}
