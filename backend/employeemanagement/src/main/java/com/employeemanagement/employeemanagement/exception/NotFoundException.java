package com.employeemanagement.employeemanagement.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String sms){
        super(sms);
    }
}
