package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
