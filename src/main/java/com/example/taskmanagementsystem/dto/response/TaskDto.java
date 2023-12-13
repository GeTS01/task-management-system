package com.example.taskmanagementsystem.dto.response;

import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.enums.Priority;
import com.example.taskmanagementsystem.domain.enums.Status;

import java.util.Optional;

public class TaskDto {

    private long id;
    private long authorId;
    private long executorId;
    private Priority priority;
    private Status status;

    public TaskDto() {
    }

    public TaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setAuthorId(task.getAuthorId());
        taskDto.setExecutorId(task.getExecutorId());
        taskDto.setPriority(task.getPriority());
        taskDto.setStatus(task.getStatus());
    }

    public TaskDto TaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setAuthorId(task.getAuthorId());
        taskDto.setExecutorId(task.getExecutorId());
        taskDto.setPriority(task.getPriority());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setExecutorId(long executorId) {
        this.executorId = executorId;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
