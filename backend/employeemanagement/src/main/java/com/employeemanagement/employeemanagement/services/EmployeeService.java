package com.employeemanagement.employeemanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.Role;
import com.employeemanagement.employeemanagement.exception.DuplicateEntry;
import com.employeemanagement.employeemanagement.exception.NotFoundException;
import com.employeemanagement.employeemanagement.repository.EmployeeRepo;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public EmployeeDto creaEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setAddress(employeeDto.getAddress());
        employee.setRole(Role.EMPLOYEE);
        employee.setPassword(employeeDto.getPassword());

        if(employeeRepo.findByEmail(employeeDto.getEmail()).isPresent()){
            throw new DuplicateEntry("Email " + employeeDto.getEmail());
        }
        employeeRepo.save(employee);

        return employeeDto;
    }

    public String deleteEmployee(Integer id){
        if (employeeRepo.findById(id).isPresent()) {
            employeeRepo.deleteById(id);
            return "deleted employee by id: " + id;
        }else{
            throw new NotFoundException("Employee not found with id: " + id);
        }
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer id){
        Employee employee = employeeRepo.findById(id).orElseThrow(()-> new NotFoundException("Employee not found with id: " + id));
        if (employeeDto.getName() != null) employee.setName(employeeDto.getName());
        if(employeeDto.getAddress() != null) employee.setAddress(employeeDto.getAddress());

        employeeRepo.save(employee);
        return employeeDto;
    }
}
