package com.bankTransaction.dto.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BuyAirtimeResponse {
    private String message;
    private String dateOfTransaction;
    private String reference;
}
