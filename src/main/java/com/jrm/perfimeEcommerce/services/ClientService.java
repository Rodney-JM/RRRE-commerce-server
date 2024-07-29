package com.jrm.perfimeEcommerce.services;

import com.jrm.perfimeEcommerce.dto.RegistrationClientData;
import com.jrm.perfimeEcommerce.models.Client;
import com.jrm.perfimeEcommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client register(RegistrationClientData data){
        var client = new Client(data);

        clientRepository.save(client);
        return client;
    }
}
