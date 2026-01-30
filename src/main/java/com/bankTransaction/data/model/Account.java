package com.bankTransaction.data.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;


@Data
public class Account {
    @Id
    private String id;
    private String accountNumber;
    private String transactionPin;
    private User ownerOfAccount;
    private LocalDateTime transactionDate;
}
