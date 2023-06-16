package com.mohacel.edu.exception;

public class RegistrationFailException extends RuntimeException{
    public RegistrationFailException() {
    }
    public RegistrationFailException(String message){
        super(message);
    }
}
