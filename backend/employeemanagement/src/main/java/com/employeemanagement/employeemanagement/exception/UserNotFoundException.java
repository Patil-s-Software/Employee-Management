package com.employeemanagement.employeemanagement.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String sms){
        super(sms);
    }
}
