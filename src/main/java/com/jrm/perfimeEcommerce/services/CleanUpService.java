package com.jrm.perfimeEcommerce.services;

import com.jrm.perfimeEcommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CleanUpService {
    @Autowired
    private ClientRepository repository;
}
