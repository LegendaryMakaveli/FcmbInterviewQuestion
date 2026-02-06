package com.bankTransaction.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Builder
@Data
public class DepositResponse {
    private String message;
    private LocalDateTime dateOfTransaction;
}
