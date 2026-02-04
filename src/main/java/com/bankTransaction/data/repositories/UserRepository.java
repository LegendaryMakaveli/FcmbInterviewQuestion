package com.bankTransaction.data.repositories;

import com.bankTransaction.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByBvn(String bvn);
    Optional<User> findByNin(String nin);
    boolean findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);
}
