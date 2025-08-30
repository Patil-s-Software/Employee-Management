package com.employeemanagement.employeemanagement.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String status;  // PENDING, APPROVED, REJECTED
    private Long employeeId;
    private String employeeName;
    private Long managerId;
    private String managerName;
}
