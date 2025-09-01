package com.employeemanagement.employeemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.Project;


public interface ProjectRepository extends JpaRepository<Project, Integer>{
    public Optional<Project> findById(Integer id);
}
