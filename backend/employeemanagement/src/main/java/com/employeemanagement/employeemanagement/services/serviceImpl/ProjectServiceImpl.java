package com.employeemanagement.employeemanagement.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.employeemanagement.employeemanagement.dto.ProjectDto;
import com.employeemanagement.employeemanagement.entity.Project;
import com.employeemanagement.employeemanagement.entity.User;
import com.employeemanagement.employeemanagement.exception.NotFoundException;
import com.employeemanagement.employeemanagement.exception.UserNotFoundException;
import com.employeemanagement.employeemanagement.repository.ProjectRepository;
import com.employeemanagement.employeemanagement.repository.UserRepo;
import com.employeemanagement.employeemanagement.services.ProjectService;

public class ProjectServiceImpl implements ProjectService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    ProjectRepository projectRepo;

    @Override
    public ProjectDto assignUserToProject(Integer projectId, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with Id: " + userId));

        Project project = projectRepo.findById(projectId).orElseThrow(() -> new NotFoundException("Project not found with Id: " + projectId));

        user.setProject(project);
        return ProjectDto.builder()
        .projectName(project.getProjectName())
        .description(project.getDescription())
        .startDate(project.getStartDate())
        .endDate(project.getEndDate())
        .managerName(project.getManager().getName())
        .build();
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        User manager = userRepo.findById(projectDto.getManagerId()).orElseThrow(() -> new UserNotFoundException("Manager not found with Id: " + projectDto.getManagerId()));
        Project project = Project.builder()
        .projectName(projectDto.getProjectName())
        .description(projectDto.getDescription())
        .startDate(projectDto.getStartDate())
        .endDate(projectDto.getEndDate())
        .manager(manager)
        .build();

        projectRepo.save(project);
        return projectDto;
    }

    @Override
    public void deleteProject(Integer id) {
        Project project = projectRepo.findById(id).orElseThrow(() -> new NotFoundException("Project not found with Id: " + id));
        projectRepo.delete(project);        
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepo.findAll();
        List<ProjectDto> projectsList = 
            projects.stream()
                .map((project) ->
                    ProjectDto.builder()
                    .projectName(project.getProjectName())
                    .description(project.getDescription())
                    .startDate(project.getStartDate())
                    .endDate(project.getEndDate())
                    .managerName(project.getManager().getName())
                    .build()
                ).toList();
        return projectsList;
    }

    @Override
    public ProjectDto getProjectById(Integer id) {
        Project project = projectRepo.findById(id).orElseThrow(() -> new NotFoundException("Project not found with Id: " + id));
        return ProjectDto.builder()
        .projectName(project.getProjectName())
        .description(project.getDescription())
        .startDate(project.getStartDate())
        .endDate(project.getEndDate())
        .managerName(project.getManager().getName())
        .build();
    }

    @Override
    public ProjectDto updateProject(Integer id, ProjectDto projectDto) {
        Project project = projectRepo.findById(id).orElseThrow(() -> new NotFoundException("Project not found with Id: " + id));
        if(projectDto.getProjectName() != null) project.setProjectName(projectDto.getProjectName());
        if(projectDto.getDescription() != null) project.setDescription(projectDto.getDescription());
        if(projectDto.getStartDate() != null) project.setStartDate(projectDto.getStartDate());
        if(projectDto.getEndDate() != null) project.setEndDate(projectDto.getEndDate());
        return projectDto;
    }

}
