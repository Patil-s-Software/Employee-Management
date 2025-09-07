package com.employeemanagement.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
    public List<Notification> findAllByUser(Integer userId);
}
