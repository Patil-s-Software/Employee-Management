package com.employeemanagement.employeemanagement.dto;

import java.time.LocalDate;

import com.employeemanagement.employeemanagement.entity.Priority;
import com.employeemanagement.employeemanagement.entity.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private String taskName;
    private String description;
    private TaskStatus status;    // TO_DO, IN_PROGRESS, DONE
    private Priority priority;  // HIGH, MEDIUM, LOW
    private LocalDate deadline;
    private Integer assignedToId;
    private String assignedToName;
    private Integer projectId;
    private String projectName;
}
