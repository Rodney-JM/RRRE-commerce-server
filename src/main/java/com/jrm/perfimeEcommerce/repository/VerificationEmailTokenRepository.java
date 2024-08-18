package com.jrm.perfimeEcommerce.repository;

import com.jrm.perfimeEcommerce.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface VerificationEmailTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByEmailAndToken(String email, String token);

    void deleteByCreatedAtBefore(Date cutOffDate);
}
