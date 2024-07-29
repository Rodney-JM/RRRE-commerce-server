package com.jrm.perfimeEcommerce.repository;

import com.jrm.perfimeEcommerce.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
