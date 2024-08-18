package com.jrm.perfimeEcommerce.services;

import com.jrm.perfimeEcommerce.models.VerificationToken;
import com.jrm.perfimeEcommerce.repository.ClientRepository;
import com.jrm.perfimeEcommerce.repository.VerificationEmailTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class TokenEmailVerificationService {
    @Autowired
    private VerificationEmailTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClientRepository clientRepository;

    public VerificationToken generateAndSendToken(String email){
        String token = String.format("%06d", new Random().nextInt(999999));

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setEmail(email);
        verificationToken.setToken(token);
        verificationToken.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        tokenRepository.save(verificationToken);
        emailService.sendVerificationEmail(email, "Verification Token", token);

        return verificationToken;
    }

    public boolean validateToken(String email, String token){
        Optional<VerificationToken> tokenOptional = tokenRepository.findByEmailAndToken(email, token);


        if(tokenOptional.isPresent()){
            var client = clientRepository.findByEmail(email);
            if (client.isPresent()) {
                client.get().setVerified(1);
                clientRepository.save(client.get());

                tokenRepository.delete(tokenOptional.get());
                return true;
            }
        }
        return false;
    }
}
