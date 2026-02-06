package com.bankTransaction.dto.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class LoginUserResponse {
    private String token;
    private String message;
    private String email;
    private LocalDateTime dateOfLogin;
    private String role;
    private Long userId;
}
