package com.jrm.perfimeEcommerce.dto;

import java.time.LocalDateTime;

public record RegistrationClientData(
                        String firstName,
                        String lastName,
                        String email,
                        String password
){
}
