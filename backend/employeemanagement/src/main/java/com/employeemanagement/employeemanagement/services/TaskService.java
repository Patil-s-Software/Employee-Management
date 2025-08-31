package com.employeemanagement.employeemanagement.services;

import com.employeemanagement.employeemanagement.dto.TaskDto;
import java.util.List;

public interface TaskService {
    List<TaskDto> getAllTasks();
    TaskDto getTaskById(Integer id);
    TaskDto createTask(TaskDto taskDto);
    TaskDto updateTask(Integer id, TaskDto taskDto);
    void deleteTask(Integer id);

    List<TaskDto> getTasksByUser(Integer userId);
    List<TaskDto> getTasksByProject(Integer projectId);
}
