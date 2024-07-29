package com.jrm.perfimeEcommerce.controllers;

import com.jrm.perfimeEcommerce.dto.DetailingClientData;
import com.jrm.perfimeEcommerce.dto.RegistrationClientData;
import com.jrm.perfimeEcommerce.services.ClientService;
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

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegistrationClientData data, UriComponentsBuilder builder){
        var client = service.register(data);

        return ResponseEntity.ok().build();

        /*var uri = builder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailingClientData(client));*/
    }
}
