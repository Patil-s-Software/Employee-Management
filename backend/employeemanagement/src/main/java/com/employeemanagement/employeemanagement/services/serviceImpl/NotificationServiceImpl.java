package com.employeemanagement.employeemanagement.services.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.employeemanagement.employeemanagement.dto.NotificationDto;
import com.employeemanagement.employeemanagement.entity.Notification;
import com.employeemanagement.employeemanagement.entity.User;
import com.employeemanagement.employeemanagement.exception.NotFoundException;
import com.employeemanagement.employeemanagement.exception.UserNotFoundException;
import com.employeemanagement.employeemanagement.repository.NotificationRepository;
import com.employeemanagement.employeemanagement.repository.UserRepo;
import com.employeemanagement.employeemanagement.services.NotificationService;

public class NotificationServiceImpl implements NotificationService{
    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public NotificationDto createNotification(NotificationDto notificationDto) {
        User user = userRepo.findById(notificationDto.getUserId()).orElseThrow(()-> new UserNotFoundException("User not found with Id: " + notificationDto.getUserId()));
        
        Notification notification = Notification.builder()
                                                .message(notificationDto.getMessage())
                                                .user(user)
                                                .build();

        notificationRepository.save(notification);
        return notificationDto;
    }

    @Override
    public void deleteNotification(Integer id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Notification not found with Id: " + id));
        notificationRepository.delete(notification);
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        List<NotificationDto> notifications = notificationRepository.findAll().stream()
                                                .map(n -> NotificationDto.builder()
                                                        .message(n.getMessage())
                                                        .userName(n.getUser().getName())
                                                        .userId(n.getUser().getId())
                                                        .createdAt(n.getCreatedAt())
                                                        .readStatus(n.isReadStatus())
                                                        // .readStatus(n.getReadStatus())
                                                        .build()
                                                ).toList();
        return notifications;
    }

    @Override
    public NotificationDto getNotificationById(Integer id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Notification not found with Id: " + id));
        return modelMapper.map(notification, NotificationDto.class);
    }

    @Override
    public List<NotificationDto> getNotificationsByUser(Integer userId) {
        userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with Id: " + userId));
        List<NotificationDto> notiList = notificationRepository.findAllByUser(userId)
                                                                .stream()
                                                                .map((notification) -> modelMapper.map(notification, NotificationDto.class))
                                                                .toList();
        return notiList;
    }

    @Override
    public void markAsRead(Integer id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Notification not found with Id: " + id));
        notification.setReadStatus(true);
        notificationRepository.save(notification);
    }
    
}
