package com.employeemanagement.employeemanagement.dto;

import com.employeemanagement.employeemanagement.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmployeeDto {
    @NotEmpty(message = "Please enter name")

    private String name;
    
    private String address;
    
    @Email(message = "Please enter a valid email address")
    private String email;
    
    private Role role = Role.EMPLOYEE;
    
    @NotEmpty(message = "Please enter password")
    private String password;
}
