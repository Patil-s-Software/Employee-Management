package com.employeemanagement.employeemanagement.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.employeemanagement.employeemanagement.dto.UserDto;

public interface UserService {
    Page<UserDto> getAllUsers(Pageable p);
    UserDto getUserById(Integer id);
    UserDto updateUser(Integer id, UserDto userDTO);
    void deleteUser(Integer id);
}