package com.bankTransaction.data.repositories;
import com.bankTransaction.data.model.Bvn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface BvnRepository extends JpaRepository<Bvn, Long> {
    Optional<Bvn> findByBvn(String bvn);
    Optional<Bvn> findByEmail(String email);
    Optional<Bvn> findByPhoneNumber(String phoneNumber);
}
