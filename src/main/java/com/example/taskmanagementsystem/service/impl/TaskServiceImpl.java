package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.dto.request.CreateTaskDto;
import com.example.taskmanagementsystem.dto.response.TaskDto;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    public final TaskRepository taskRepository;
    public TaskDto taskDto;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto create(CreateTaskDto createTaskDto) {
//        var task = createTaskDto.buildeTask(createTaskDto);
//        taskRepository.save(task);
//        return taskDto.buildTaskDto(task);
        return null;
    }

    @Override
    public TaskDto updateById(long id, CreateTaskDto createTaskDto) {
        var taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена");
        }
        var updateTask = taskRepository.updateById(
                createTaskDto.getTitle(),
                createTaskDto.getDescription(),
                createTaskDto.getPriority(),
                createTaskDto.getStatus(),
                createTaskDto.getExecutorId(),
                id);
        return taskDto.buildTaskDto(updateTask);
    }

    @Override
    public void deleteById(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Optional<TaskDto> findById(long id) {
        var taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Задача не найдена");
        }
        var get = taskOptional.get();
        return Optional.of(taskDto.buildTaskDto(get));
    }

    @Override
    public Optional<TaskDto> changeStatus(long id) {
        var taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена");
        }
        var task = taskRepository.updateAuthorById(id);
        return Optional.of(taskDto.buildTaskDto(task));
    }
}
