package com.employeemanagement.employeemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

    public Optional<Employee> findByEmail(String email);
    
}
