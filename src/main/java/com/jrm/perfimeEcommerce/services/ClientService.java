package com.jrm.perfimeEcommerce.services;

import com.jrm.perfimeEcommerce.dto.LoginClientData;
import com.jrm.perfimeEcommerce.dto.LoginClientErrorData;
import com.jrm.perfimeEcommerce.dto.RegistrationClientData;
import com.jrm.perfimeEcommerce.dto.VerifyClientData;
import com.jrm.perfimeEcommerce.models.Client;
import com.jrm.perfimeEcommerce.repository.ClientRepository;
import com.jrm.perfimeEcommerce.infra.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenEmailVerificationService tokenEmailVerificationService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Client register(RegistrationClientData data){
        Optional<Client> client = clientRepository.findByEmail(data.email());
        if(client.isPresent()){
            throw new UserAlreadyExistsException("User with email "+ data.email() + " already exists");
        }

        Client newClient = new Client(data);
        clientRepository.save(newClient);

        return newClient;
    }

    public boolean verify(VerifyClientData clientId){
        return tokenEmailVerificationService.validateToken(clientId.email(), clientId.token());
    }

    public LoginClientErrorData login(LoginClientData loginClientData){
        Optional<Client> client = clientRepository.findByEmail(loginClientData.email());
        if(client.isPresent()){
            boolean clientCredentials =  passwordEncoder.matches(loginClientData.password(), client.get().getPassword())  && client.get().getVerified() == 1;
            if(clientCredentials){
                return new LoginClientErrorData(true, "");
            }else{
                return new LoginClientErrorData(false, "The password is wrong, try again");
            }
        }else{
            return new LoginClientErrorData(false, "The email was not found");
        }
    }
}
