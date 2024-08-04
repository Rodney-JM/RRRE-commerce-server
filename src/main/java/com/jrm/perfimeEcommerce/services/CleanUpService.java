package com.jrm.perfimeEcommerce.services;

import com.jrm.perfimeEcommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CleanUpService {
    @Autowired
    private ClientRepository repository;


    @Scheduled(fixedRate = 60000)
    @Transactional
    public void cleanUpAllUnverifiedUsers(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime cutOff = now.minusMinutes(60);

        Date cutOffDate = Date.from(cutOff.atZone(ZoneId.systemDefault()).toInstant());

        //remove registros com verified = false e created_at < cutOffDate
        repository.deleteByVerifiedFalseAndCreatedAtBefore(cutOffDate);
    }
}
