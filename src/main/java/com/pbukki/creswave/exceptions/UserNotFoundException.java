package com.pbukki.creswave.exceptions;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String resourceName, String fieldName,String username) {
        super(String.format("%s not found with %s %s",resourceName,fieldName,username));
    }
}
