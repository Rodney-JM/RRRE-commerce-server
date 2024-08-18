package com.jrm.perfimeEcommerce.controllers;

import com.jrm.perfimeEcommerce.dto.DetailingClientData;
import com.jrm.perfimeEcommerce.dto.LoginClientData;
import com.jrm.perfimeEcommerce.dto.RegistrationClientData;
import com.jrm.perfimeEcommerce.dto.VerifyClientData;
import com.jrm.perfimeEcommerce.services.ClientService;
import com.jrm.perfimeEcommerce.services.TokenEmailVerificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @Autowired
    private TokenEmailVerificationService tokenEmailVerificationService;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegistrationClientData data, UriComponentsBuilder builder){
        var client = service.register(data);

        var uri = builder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();

        var verificationToken = tokenEmailVerificationService.generateAndSendToken(client.getEmail());
        return ResponseEntity.created(uri).body(new DetailingClientData(client));
    }

    @PostMapping("/verify")
    public ResponseEntity verify(@RequestBody VerifyClientData clientId){
        boolean isValid = service.verify(clientId);

        if(isValid){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity login(@RequestBody LoginClientData loginClientData){
        boolean client = service.login(loginClientData);

        if(client){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
