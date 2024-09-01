package com.jrm.perfimeEcommerce.infra.exceptions;

public class UserDoesntExists extends RuntimeException{
    public UserDoesntExists(String message){
        super(message);
    }
}
