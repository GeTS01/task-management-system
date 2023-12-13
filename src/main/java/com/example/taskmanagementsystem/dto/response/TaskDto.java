package com.example.taskmanagementsystem.dto.response;

import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.enums.Priority;
import com.example.taskmanagementsystem.domain.enums.Status;

import java.util.Optional;

public class TaskDto {

    private long id;
    private long executorId;
    private Priority priority;
    private Status status;


    public TaskDto() {
    }

    public TaskDto(long id,  long executorId, Priority priority, Status status) {
        this.id = id;
        this.executorId = executorId;
        this.priority = priority;
        this.status = status;
    }

    public TaskDto buildTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setExecutorId(task.getExecutorId());
        taskDto.setPriority(task.getPriority());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(long executorId) {
        this.executorId = executorId;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
