package com.mohacel.edu.exception;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException() {
    }
    public UserIdNotFoundException(String message) {
        super(message);
    }
}
