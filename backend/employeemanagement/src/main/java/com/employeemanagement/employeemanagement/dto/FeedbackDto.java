package com.employeemanagement.employeemanagement.dto;

import lombok.Data;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
    private Long id;
    private String comments;
    private int rating; // 1–5
    private LocalDate givenDate;
    private Long givenById;
    private String givenByName;
    private Long givenToId;
    private String givenToName;
}
