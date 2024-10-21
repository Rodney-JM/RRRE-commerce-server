package com.jrm.perfimeEcommerce.infra.exceptions;

public class EmailNotFound extends RuntimeException{
    public EmailNotFound(String message){
        super(message);
    }
}
