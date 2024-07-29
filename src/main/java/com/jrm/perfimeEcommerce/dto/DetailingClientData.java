package com.jrm.perfimeEcommerce.dto;

import com.jrm.perfimeEcommerce.models.Client;

public record DetailingClientData(
        Long id,
        String firstName,
        String lastName,
        String email,
        String password
) {
    public DetailingClientData(Client client){
        this(client.getId(), client.getFirstName(), client.getLastName(), client.getEmail(), client.getPassword());
    }
}
