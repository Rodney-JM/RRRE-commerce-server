package com.jrm.perfimeEcommerce.repository;

import com.jrm.perfimeEcommerce.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;

public interface ClientRepository extends JpaRepository<Client, Long> {
    void deleteByVerifiedFalseAndCreatedAtBefore(Date cutOffDate);
}
