package com.jrm.perfimeEcommerce.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "verifications_tokens")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String token;
}
