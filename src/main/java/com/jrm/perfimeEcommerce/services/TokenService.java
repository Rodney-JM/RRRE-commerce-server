package com.jrm.perfimeEcommerce.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jrm.perfimeEcommerce.models.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("api.security.token.secret")
    private String secret;

    public String gerarToken(Client client){
        try{
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Perfime Ecommerce")
                    .withSubject(client.getEmail())
                    .withClaim("id", client.getId())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error to create the token JWT ", exception);
        }
    }

    public String getSubject(String tokenJWT){
        DecodedJWT decodedJWT;
        try{
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Perfime Ecommerce")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }catch (JWTVerificationException exception){
            throw new RuntimeException("JWT Token invalid");
        }
    }


    private Instant expirationDate(){
        return LocalDateTime.now().plusWeeks(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
