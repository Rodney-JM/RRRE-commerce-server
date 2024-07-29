package com.jrm.perfimeEcommerce.models;

import com.jrm.perfimeEcommerce.dto.RegistrationClientData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    private Integer verified;

    private LocalDateTime created_at;

    public Client(RegistrationClientData data){
        this.email = data.email();
        this.password = data.password();
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.verified = 0;
        this.created_at = LocalDateTime.now();
    }
}
