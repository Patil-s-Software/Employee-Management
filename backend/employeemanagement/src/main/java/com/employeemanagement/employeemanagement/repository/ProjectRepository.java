package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
    
}
