package com.example.taskmanagementsystem.controller;
import com.example.taskmanagementsystem.domain.enums.Role;
import com.example.taskmanagementsystem.dto.request.CreateTaskDto;
import com.example.taskmanagementsystem.dto.response.TaskDto;
import com.example.taskmanagementsystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @PreAuthorize(Role.CLIENT_NAME)
    public TaskDto createTask(@RequestBody CreateTaskDto createTaskDto) {
        return taskService.create(createTaskDto);
    }
}