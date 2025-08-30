package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
    
}
