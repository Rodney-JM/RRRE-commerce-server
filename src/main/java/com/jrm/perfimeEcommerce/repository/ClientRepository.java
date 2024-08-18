package com.jrm.perfimeEcommerce.repository;

import com.jrm.perfimeEcommerce.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    void deleteByVerifiedFalseAndCreatedAtBefore(Date cutOffDate);

    Optional<Client> findByEmail(String email);
}
