package com.bankTransaction.data.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction-records")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String transactionReference;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal transactionFees;

    @Column(nullable = false)
    private String senderAccountNumber;

    private String destinationAccountNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private BigDecimal amount;

    private String description;
    private String status;
    private LocalDateTime dateOfTransaction;
}