package com.recykal.ticketer.Exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super(email);
    }
}
