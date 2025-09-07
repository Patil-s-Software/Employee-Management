package com.employeemanagement.employeemanagement.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.UserDto;
import com.employeemanagement.employeemanagement.entity.User;
import com.employeemanagement.employeemanagement.exception.UserNotFoundException;
import com.employeemanagement.employeemanagement.repository.UserRepo;
import com.employeemanagement.employeemanagement.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepository;

    @Override
    @CacheEvict(value = "users")
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "users")
    public Page<UserDto> getAllUsers(Pageable pageable) {
        System.out.println("Called\n\n\n\n\n");
        return userRepository.findAll(pageable)
                                .map(user -> UserDto.builder()
                                .name(user.getName())
                                .address(user.getAddress())
                                .email(user.getEmail())
                                .department(user.getDepartment())
                                .designation(user.getDesignation())
                                .role(user.getRole())
                                .profilePic(user.getProfilePic())
                                .build()
                            );
    }

    @Override
    public UserDto getUserById(Integer id) {
        System.out.println("users");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found with id: " + id));
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setDepartment(user.getDepartment());
        userDto.setDesignation(user.getDesignation());
        userDto.setRole(user.getRole());
        return userDto;
    }

    @Override
    @CacheEvict(value = "users")
    public UserDto updateUser(Integer id, UserDto userDTO) {
        // 1️⃣ Find user by id
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // 2️⃣ Update fields
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setDesignation(userDTO.getDesignation());
        user.setProfilePic(userDTO.getProfilePic());

        // 3️⃣ Save updated user
        User updatedUser = userRepository.save(user);

        // 4️⃣ Convert entity -> DTO
        return UserDto.builder()
                .name(updatedUser.getName())
                .address(updatedUser.getAddress())
                .email(updatedUser.getEmail())
                .role(updatedUser.getRole())
                .department(updatedUser.getDepartment() != null ? updatedUser.getDepartment() : null)
                .designation(updatedUser.getDesignation())
                .profilePic(updatedUser.getProfilePic())
                .build();
    }

}
