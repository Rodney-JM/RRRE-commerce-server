package com.jrm.perfimeEcommerce.controllers;

import com.jrm.perfimeEcommerce.dto.LoginClientData;
import com.jrm.perfimeEcommerce.dto.TokenJWTData;
import com.jrm.perfimeEcommerce.models.Client;
import com.jrm.perfimeEcommerce.services.AuthenticationService;
import com.jrm.perfimeEcommerce.services.ClientService;
import com.jrm.perfimeEcommerce.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClientService clientService;

    @PostMapping
    @Transactional
    public ResponseEntity login(@RequestBody LoginClientData loginClientData){
        boolean client = clientService.login(loginClientData);

        if(client){
            var authenticationToken = new UsernamePasswordAuthenticationToken(loginClientData.email(), loginClientData.password());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.gerarToken((Client) authentication.getPrincipal());
            return ResponseEntity.ok(new TokenJWTData(tokenJWT));
        }

        return ResponseEntity.badRequest().build();
    }
}

