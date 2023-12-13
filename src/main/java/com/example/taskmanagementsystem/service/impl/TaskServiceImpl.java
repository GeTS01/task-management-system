package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.dto.request.CreateTaskDto;
import com.example.taskmanagementsystem.dto.response.TaskDto;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    public final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto create(CreateTaskDto createTaskDto) {
        var task = createTaskDto.CreateTaskDto(createTaskDto);
        taskRepository.save(task);
        TaskDto taskDto = new TaskDto();
        var taskResponse = taskDto.TaskDto(task);
        return taskResponse;
    }

    @Override
    public Optional<TaskDto> updateById(long id, CreateTaskDto createTaskDto) {
        var taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена");
        }
        var updateTask = taskRepository.updateById(
                createTaskDto.getTitle(),
                createTaskDto.getDescription(),
                createTaskDto.getPriority(),
                createTaskDto.getStatus(),
                createTaskDto.getAuthorId(),
                createTaskDto.getExecutorId(),
                id);
        var taskDto = new TaskDto(updateTask);
        return Optional.of(taskDto);
    }

    @Override
    public void deleteById(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Optional<TaskDto> findById(long id) {
        var taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена");
        }
        var get = taskOptional.get();
        var taskDto = new TaskDto(get);
        return Optional.of(taskDto);
    }

    @Override
    public Optional<TaskDto> changeStatus(long id, long authorId) {
        var taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена");
        }
        var task = taskRepository.updateAuthorById(authorId, id);
        var taskDto = new TaskDto(task);
        return Optional.of(taskDto);
    }

    @Override
    public Optional<List<TaskDto>> findListUsersTasks(long id, long authorId) {
        boolean existsTask = taskRepository.existsById(id);
        if (!existsTask) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена");
        }
        var task = taskRepository.getListTaskByAuthorId(authorId);
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (var item : task) {
            var taskDto = new TaskDto(item);
            taskDtoList.add(taskDto);
        }
        return Optional.of(taskDtoList);
    }
}
