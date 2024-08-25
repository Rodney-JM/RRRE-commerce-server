package com.jrm.perfimeEcommerce.services;

import com.jrm.perfimeEcommerce.dto.LoginClientData;
import com.jrm.perfimeEcommerce.models.Client;
import com.jrm.perfimeEcommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private ClientRepository clientRepository;

    public boolean login(LoginClientData loginClientData){
        Optional<Client> client = clientRepository.findByEmail(loginClientData.email());

        if(client.isPresent()){
            if(Objects.equals(client.get().getPassword(), loginClientData.password()) && client.get().getVerified()==1){
                return true;
            }
        }

        return false;
    }
}
