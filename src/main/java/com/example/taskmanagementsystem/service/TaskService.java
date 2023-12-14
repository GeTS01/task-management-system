package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.domain.enums.Status;
import com.example.taskmanagementsystem.dto.request.CreateTaskDto;
import com.example.taskmanagementsystem.dto.response.TaskDto;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

public interface TaskService {

    void create(CreateTaskDto createTaskDto);
    void update(long id, CreateTaskDto createTaskDto);
    void delete(long id);
    TaskDto read(long id);
    void updateStatus(long id, Status status);

}
