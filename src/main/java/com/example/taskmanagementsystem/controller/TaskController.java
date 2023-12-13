package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.domain.enums.Role;
import com.example.taskmanagementsystem.dto.request.CreateTaskDto;
import com.example.taskmanagementsystem.dto.response.TaskDto;
import com.example.taskmanagementsystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @PreAuthorize(Role.CLIENT_NAME)
    public TaskDto createTask(@RequestBody CreateTaskDto createTaskDto) {
        return taskService.create(createTaskDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize(Role.CLIENT_NAME)
    public ResponseEntity<TaskDto> updateTaskById(@PathVariable long id, @RequestBody CreateTaskDto createTaskDto) {
        Optional<TaskDto> updatedTask = taskService.updateById(id, createTaskDto);
        return updatedTask.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(Role.CLIENT_NAME)
    public void deleteTaskById(@PathVariable long id) {
        taskService.deleteById(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize(Role.CLIENT_NAME)
    public ResponseEntity<TaskDto> findTaskById(@PathVariable long id) {
        Optional<TaskDto> task = taskService.findById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/changeStatus")
    @PreAuthorize(Role.CLIENT_NAME)
    public ResponseEntity<TaskDto> changeTaskStatus(@PathVariable long id, @RequestParam long authorId) {
        Optional<TaskDto> updatedTask = taskService.changeStatus(id, authorId);
        return updatedTask.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{id}")
    @PreAuthorize(Role.CLIENT_NAME)
    public ResponseEntity<List<TaskDto>> findUserTasks(@PathVariable long id, @RequestParam long userId) {
        Optional<List<TaskDto>> tasks = taskService.findListUsersTasks(id, userId);
        return tasks.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}