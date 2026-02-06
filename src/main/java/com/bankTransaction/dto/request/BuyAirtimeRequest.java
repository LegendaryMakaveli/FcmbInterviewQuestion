package com.bankTransaction.dto.request;


import com.bankTransaction.data.model.AirtimeProvider;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyAirtimeRequest {
    private String accountNumber;
    private AirtimeProvider networkProvider;
    private BigDecimal amount;
    private String phoneNumber;
}
