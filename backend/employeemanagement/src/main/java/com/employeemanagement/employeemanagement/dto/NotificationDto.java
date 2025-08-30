package com.employeemanagement.employeemanagement.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private Long id;
    private String message;
    private boolean readStatus;
    private LocalDateTime createdAt;
    private Long userId;
    private String userName;
}
