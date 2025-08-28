package com.employeemanagement.employeemanagement.exception;

public class DuplicateEntry extends RuntimeException{
    public DuplicateEntry(String sms){
        super(sms);
    }
}
