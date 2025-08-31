package com.employeemanagement.employeemanagement.services;

import com.employeemanagement.employeemanagement.dto.NotificationDto;
import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAllNotifications();
    NotificationDto getNotificationById(Integer id);
    NotificationDto createNotification(NotificationDto notificationDto);
    void deleteNotification(Integer id);

    List<NotificationDto> getNotificationsByUser(Integer userId);
    void markAsRead(Integer id);
}
