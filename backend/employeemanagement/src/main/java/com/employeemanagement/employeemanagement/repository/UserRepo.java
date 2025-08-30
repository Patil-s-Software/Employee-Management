package com.employeemanagement.employeemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

    public Optional<User> findByEmail(String email);
    public Optional<User> findById(Integer id);
}
