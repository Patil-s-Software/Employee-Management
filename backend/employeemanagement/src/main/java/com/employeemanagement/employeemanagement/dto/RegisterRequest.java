package com.employeemanagement.employeemanagement.dto;

import lombok.Data;

// User Registration Request
@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String department;
    private String designation;
    private String phone;
    private String address;
}