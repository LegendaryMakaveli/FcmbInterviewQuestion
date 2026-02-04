package com.bankTransaction.dto.request;


import com.bankTransaction.data.model.Bvn;
import lombok.Data;

@Data
public class VerifyNinRequest {
    private String nin;
    private Bvn bvn;
}
