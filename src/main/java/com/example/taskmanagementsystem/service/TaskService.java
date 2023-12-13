package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dto.request.CreateTaskDto;
import com.example.taskmanagementsystem.dto.response.TaskDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskDto create(CreateTaskDto createTaskDto);
    TaskDto updateById(long id, CreateTaskDto createTaskDto);
    void deleteById(long id);
    Optional<TaskDto> findById(long id);
    Optional<TaskDto> changeStatus(long id);

}
