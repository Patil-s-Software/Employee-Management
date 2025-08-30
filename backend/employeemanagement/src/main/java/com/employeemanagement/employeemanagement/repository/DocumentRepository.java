package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{

}
