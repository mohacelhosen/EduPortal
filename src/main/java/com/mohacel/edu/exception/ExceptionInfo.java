package com.mohacel.edu.exception;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ExceptionInfo {
    public String exceptionMessage;
    public String exceptionCode;
    public LocalDate timeStamp;
}
