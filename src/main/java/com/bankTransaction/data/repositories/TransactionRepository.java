package com.bankTransaction.data.repositories;

import com.bankTransaction.data.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query("SELECT t FROM Transaction t WHERE t.senderAccountNumber = :accNo " + "OR t.destinationAccountNumber = :accNo")
    Page<Transaction> findAllByAccountNumber(@Param("accNo") String accountNumber, Pageable pageable);

    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.senderAccountNumber = :accNo " + "AND t.dateOfTransaction >= :startOfMonth AND t.status = 'SUCCESS'")
    long countTransactionsThisMonth(@Param("accNo") String accNo, @Param("startOfMonth") LocalDateTime startOfMonth);
}
