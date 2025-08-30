package com.employeemanagement.employeemanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.employeemanagement.dto.UserDto;
import com.employeemanagement.employeemanagement.entity.User;
import com.employeemanagement.employeemanagement.services.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService employeeService;

    @GetMapping
    public ResponseEntity<List<User>> getAllEmployee(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }    

    @PostMapping
    public ResponseEntity<UserDto> createEmployee(@Valid @RequestBody UserDto employeeDto) {
        return new ResponseEntity<>(employeeService.creaEmployee(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateEmployee(@PathVariable Integer id, @RequestBody UserDto employeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDto, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.GONE);
    }
}
