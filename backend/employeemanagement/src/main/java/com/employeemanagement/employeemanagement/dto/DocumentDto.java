package com.employeemanagement.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    private Long id;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private Long uploadedById;
    private String uploadedByName;
}
