package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{

}
