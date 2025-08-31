package com.employeemanagement.employeemanagement.services;

import com.employeemanagement.employeemanagement.dto.ProjectDto;
import java.util.List;

public interface ProjectService {
    List<ProjectDto> getAllProjects();
    ProjectDto getProjectById(Integer id);
    ProjectDto createProject(ProjectDto projectDto);
    ProjectDto updateProject(Integer id, ProjectDto projectDto);
    void deleteProject(Integer id);

    ProjectDto assignUserToProject(Integer projectId, Integer userId);
}
