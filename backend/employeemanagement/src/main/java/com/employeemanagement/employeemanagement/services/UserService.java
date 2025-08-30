package com.employeemanagement.employeemanagement.services;

import java.util.List;

import com.employeemanagement.employeemanagement.dto.UserDto;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Integer id);
    UserDto updateUser(Integer id, UserDto userDTO);
    void deleteUser(Integer id);
}