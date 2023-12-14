package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.enums.Status;
import com.example.taskmanagementsystem.dto.request.CreateTaskDto;
import com.example.taskmanagementsystem.dto.response.TaskDto;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.repository.UserRepository;
import com.example.taskmanagementsystem.service.AuthorizedUserService;
import com.example.taskmanagementsystem.service.TaskService;
import com.example.taskmanagementsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final AuthorizedUserService authorizedUserService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService, UserRepository userRepository, AuthorizedUserService authorizedUserService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.authorizedUserService = authorizedUserService;
    }


    /**
     * Метод для создания задачи
     */
    @Override
    public void create(CreateTaskDto createTaskDto) {
        var authorId = authorizedUserService.getUser().getId();
        var email = userRepository.findByEmail(createTaskDto.getEmail());
        if (email.isEmpty()) {
            throw new EntityNotFoundException("Email with id " + createTaskDto.getEmail() + " not found");
        }
        var executorId = email.get();
        var task = new Task(
                createTaskDto.getTitle(),
                createTaskDto.getDescription(),
                createTaskDto.getPriority(),
                createTaskDto.getStatus(),
                authorId,
                executorId.getId());
        task.setCreateAt(ZonedDateTime.now());
        taskRepository.save(task);
    }


    /**
     * Метод для обновления задачи по id (идентификатору)
     */
    @Override
    public void update(long id, CreateTaskDto createTaskDto) {
        var authorId = authorizedUserService.getUser().getId();
        var task = taskRepository.findByIdAndAuthorId(id, authorId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена")
        );
        var executor = userRepository.findByEmail(createTaskDto.getEmail()).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("Исполнитель с [email=%s] не найден", createTaskDto.getEmail())
                )
        );
        task.setExecutorId(executor.getId());
        task.setDescription(createTaskDto.getDescription());
        task.setPriority(createTaskDto.getPriority());
        task.setStatus(createTaskDto.getStatus());
        task.setTitle(createTaskDto.getTitle());
        task = taskRepository.save(task);
    }

    /**
     * Метод для удаления задачи
     */
    @Override
    public void delete(long id) {
        var authorId = authorizedUserService.getUser().getId();
        var task = taskRepository.findByIdAndAuthorId(id, authorId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена")
        );
        taskRepository.deleteById(task.getId());
    }

    /**
     * Метод для получения задачи id (идентификатору)
     */
    @Override
    public TaskDto read(long id) {
        var authorId = authorizedUserService.getUser().getId();
        var task = taskRepository.findByIdAndAuthorId(id, authorId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена")
        );
        return new TaskDto(task);
    }

    /**
     * Метод для обновления статуса задачи
     */
    @Override
    public void updateStatus(long id, Status status) {
        var authorId = authorizedUserService.getUser().getId();
        var task = taskRepository.findByIdAndAuthorId(id, authorId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена")
        );
        task.setStatus(status);
        taskRepository.save(task);
    }
}
