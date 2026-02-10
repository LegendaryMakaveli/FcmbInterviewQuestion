package com.bankTransaction.dto.response;


import lombok.Builder;
import lombok.Data;

import com.bankTransaction.data.model.AccountType;
import java.math.BigDecimal;
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
    private String accountNumber;
    private String firstName;
    private String lastName;
    private BigDecimal balance;
    private AccountType accountType;
}
