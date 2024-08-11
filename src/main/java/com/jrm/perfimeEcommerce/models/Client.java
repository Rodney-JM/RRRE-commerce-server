package com.jrm.perfimeEcommerce.models;

import com.jrm.perfimeEcommerce.dto.RegistrationClientData;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
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

    @Column(name = "created_at")
    private Date createdAt;

    public Client(RegistrationClientData data){
        this.email = data.email();
        this.password = data.password();
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.verified = 0;
        this.createdAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
}
