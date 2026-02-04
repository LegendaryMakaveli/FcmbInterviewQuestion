package com.bankTransaction.dto.response;

import lombok.Data;

@Data
public class ApiResponses {
    private boolean isSuccessful;
    private String message;
    private Object data;


    public ApiResponses(boolean isSuccessful, Object data) {
        this.isSuccessful = isSuccessful;
        this.data = data;
    }

    public ApiResponses(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }
}
