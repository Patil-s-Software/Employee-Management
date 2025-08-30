package com.employeemanagement.employeemanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.employeemanagement.dto.UserDto;
import com.employeemanagement.employeemanagement.entity.User;
import com.employeemanagement.employeemanagement.services.serviceImpl.UserServiceImpl;

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
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllEmployee(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateEmployee(@PathVariable Integer id, @RequestBody UserDto employeeDto) {
        return new ResponseEntity<>(userService.updateUser(id, employeeDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Deleted Success", HttpStatus.GONE);
    }
}
