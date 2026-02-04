package com.bankTransaction.data.repositories;

import com.bankTransaction.data.model.Nin;
import com.bankTransaction.dto.request.FindByUserDataRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface NinRepository extends JpaRepository<Nin, Long> {
    Optional<Nin> findByNin(String nin);
    Optional<Nin> findByEmail(String email);
    List<Nin> findByFirstNameAndLastNameAndDateOfBirth(FindByUserDataRequest request);
}
