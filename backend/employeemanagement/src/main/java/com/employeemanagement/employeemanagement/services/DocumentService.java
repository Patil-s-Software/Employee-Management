package com.employeemanagement.employeemanagement.services;

import com.employeemanagement.employeemanagement.dto.DocumentDto;
import java.util.List;

public interface DocumentService {
    List<DocumentDto> getAllDocuments();
    DocumentDto getDocumentById(Integer id);
    DocumentDto uploadDocument(DocumentDto documentDto);
    void deleteDocument(Integer id);

    List<DocumentDto> getDocumentsByUser(Integer userId);
}
