package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.domain.enums.Role;
import com.example.taskmanagementsystem.domain.enums.Status;
import com.example.taskmanagementsystem.dto.request.CreateTaskDto;
import com.example.taskmanagementsystem.dto.response.TaskDto;
import com.example.taskmanagementsystem.service.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @PreAuthorize(Role.CLIENT_NAME)
    public void create(@RequestBody @Valid CreateTaskDto createTaskDto) {
        taskService.create(createTaskDto);
    }

    @PutMapping("/id")
    @PreAuthorize(Role.CLIENT_NAME)
    public void update(@RequestParam long id, @RequestBody @Valid CreateTaskDto createTaskDto) {
        taskService.update(id, createTaskDto);
    }

    @DeleteMapping("/id")
    @PreAuthorize(Role.CLIENT_NAME)
    public void delete(@RequestParam long id) {
        taskService.delete(id);
    }

    @GetMapping("/id")
    @PreAuthorize(Role.CLIENT_NAME)
    public TaskDto find(@RequestParam long id) {
        return taskService.read(id);
    }

    @PatchMapping("/id/changeStatus")
    @PreAuthorize(Role.CLIENT_NAME)
    public void updateStatus(@RequestParam long id, @RequestParam Status status) {
        taskService.updateStatus(id, status);
    }
}
