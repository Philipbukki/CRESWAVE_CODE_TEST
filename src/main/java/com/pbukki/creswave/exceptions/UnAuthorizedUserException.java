package com.pbukki.creswave.exceptions;

public class UnAuthorizedUserException extends RuntimeException{
    public UnAuthorizedUserException(String message) {
        super(message);
    }
}
