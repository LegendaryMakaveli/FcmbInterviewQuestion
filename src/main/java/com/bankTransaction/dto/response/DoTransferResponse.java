package com.bankTransaction.dto.response;


import lombok.Data;

@Data
public class DoTransferResponse {
    private String message;
    private String dateOfTransaction;
}
