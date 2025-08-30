package com.employeemanagement.employeemanagement.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String taskName;
    private String description;
    private String status;    // TO_DO, IN_PROGRESS, DONE
    private String priority;  // HIGH, MEDIUM, LOW
    private LocalDate deadline;
    private Long assignedToId;
    private String assignedToName;
    private Long projectId;
    private String projectName;
}
