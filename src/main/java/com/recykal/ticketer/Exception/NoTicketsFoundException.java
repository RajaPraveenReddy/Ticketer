package com.recykal.ticketer.Exception;

public class NoTicketsFoundException extends RuntimeException{
    public NoTicketsFoundException(String message) {
        super(message);
    }
}
