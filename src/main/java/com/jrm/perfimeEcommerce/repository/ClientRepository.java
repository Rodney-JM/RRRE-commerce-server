package com.jrm.perfimeEcommerce.repository;

import com.jrm.perfimeEcommerce.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    void deleteByVerifiedFalseAndCreatedAtBefore(Date cutOffDate);

    Client findByEmail(String email);
}
