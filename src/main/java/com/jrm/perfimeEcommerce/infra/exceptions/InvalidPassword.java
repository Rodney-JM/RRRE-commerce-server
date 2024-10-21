package com.jrm.perfimeEcommerce.infra.exceptions;

public class InvalidPassword extends RuntimeException{
    public InvalidPassword(String message){
        super(message);
    }
}
