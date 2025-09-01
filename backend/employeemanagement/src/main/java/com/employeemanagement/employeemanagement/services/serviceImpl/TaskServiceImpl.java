package com.employeemanagement.employeemanagement.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.employeemanagement.employeemanagement.dto.TaskDto;
import com.employeemanagement.employeemanagement.entity.Project;
import com.employeemanagement.employeemanagement.entity.Task;
import com.employeemanagement.employeemanagement.entity.User;
import com.employeemanagement.employeemanagement.exception.NotFoundException;
import com.employeemanagement.employeemanagement.exception.UserNotFoundException;
import com.employeemanagement.employeemanagement.repository.ProjectRepository;
import com.employeemanagement.employeemanagement.repository.TaskRepository;
import com.employeemanagement.employeemanagement.repository.UserRepo;
import com.employeemanagement.employeemanagement.services.TaskService;

public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private UserRepo userRepo;

    private ProjectRepository projectRepo;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        User user = userRepo.findById(taskDto.getAssignedToId()).orElseThrow(() -> new UserNotFoundException("User not found with Id: " + taskDto.getAssignedToId()));
        Project project = projectRepo.findById(taskDto.getProjectId()).orElseThrow(() -> new NotFoundException("Project not found with Id: " + taskDto.getProjectId()));
        
        Task task = Task.builder()
                        .taskName(taskDto.getTaskName())
                        .description(taskDto.getDescription())
                        .deadLine(taskDto.getDeadline())
                        .assignedTo(user)
                        .project(project)
                        .priority(taskDto.getPriority())
                        .build();

        taskRepo.save(task);
        return taskDto;
    }

    @Override
    public void deleteTask(Integer id) {
        Project project = projectRepo.findById(id).orElseThrow(() -> new NotFoundException("Project not found with Id: " + id));
        projectRepo.delete(project);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        List<TaskDto> taskList = tasks.stream()
                                        .map((task) -> TaskDto.builder()
                                                                .taskName(task.getTaskName())
                                                                .description(task.getDescription())
                                                                .deadline(task.getDeadLine())
                                                                .priority(task.getPriority())
                                                                .status(task.getTaskStatus())
                                                                .assignedToName(task.getAssignedTo().getName())
                                                                .projectName(task.getProject().getProjectName())
                                                                .build()
                                        )
                                        .toList();
        return taskList;
    }

    @Override
    public TaskDto getTaskById(Integer id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new NotFoundException("Task not found with Id: " + id));
        TaskDto taskDto = TaskDto.builder()
                                .taskName(task.getTaskName())
                                .description(task.getDescription())
                                .deadline(task.getDeadLine())
                                .priority(task.getPriority())
                                .status(task.getTaskStatus())
                                .assignedToName(task.getAssignedTo().getName())
                                .projectName(task.getProject().getProjectName())
                                .build();
        return taskDto;
    }

    @Override
    public List<TaskDto> getTasksByProject(Integer projectId) {
        Project project = projectRepo.findById(projectId).orElseThrow(() -> new NotFoundException("Project not found with Id: " + projectId));
        
        List<Task> tasks = taskRepo.findAll();
        List<TaskDto> taskList = tasks.stream()
                                        .map((task) -> TaskDto.builder()
                                                                .taskName(task.getTaskName())
                                                                .description(task.getDescription())
                                                                .deadline(task.getDeadLine())
                                                                .priority(task.getPriority())
                                                                .status(task.getTaskStatus())
                                                                .assignedToName(task.getAssignedTo().getName())
                                                                .projectName(task.getProject().getProjectName())
                                                                .build()
                                        )
                                        .filter((task) -> task.getProjectId() == project.getId())
                                        .toList();
        return taskList;
    }

    @Override
    public List<TaskDto> getTasksByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with Id: " + userId));
        
        List<Task> tasks = taskRepo.findAll();
        List<TaskDto> taskList = tasks.stream()
                                        .map((task) -> TaskDto.builder()
                                                                .taskName(task.getTaskName())
                                                                .description(task.getDescription())
                                                                .deadline(task.getDeadLine())
                                                                .priority(task.getPriority())
                                                                .status(task.getTaskStatus())
                                                                .assignedToName(task.getAssignedTo().getName())
                                                                .projectName(task.getProject().getProjectName())
                                                                .build()
                                        )
                                        .filter((task) -> task.getAssignedToId() == user.getId())
                                        .toList();
        return taskList;
    }

    @Override
    public TaskDto updateTask(Integer id, TaskDto taskDto) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new NotFoundException("Task not found with Id: " + id));
        if(taskDto.getTaskName() != null) task.setTaskName(taskDto.getTaskName());
        if(taskDto.getDescription() != null) task.setDescription(taskDto.getDescription());
        if(taskDto.getDeadline() != null) task.setDeadLine(task.getDeadLine());
        if(taskDto.getStatus() != null) task.setTaskStatus(taskDto.getStatus());
        if(taskDto.getPriority() != null) task.setPriority(taskDto.getPriority());
        return taskDto;
    }

}
