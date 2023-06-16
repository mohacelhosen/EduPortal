package com.mohacel.edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(value = RegistrationFailException.class)
    public ResponseEntity<ExceptionInfo> handleRegistrationFailException(RegistrationFailException rfe){
        String message = rfe.getMessage();
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setExceptionCode("REG-101");
        exceptionInfo.setExceptionMessage(message);
        exceptionInfo.setTimeStamp(LocalDate.now());

        return  new ResponseEntity<>(exceptionInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
