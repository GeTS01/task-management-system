package com.example.taskmanagementsystem.dto.response;

import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.User;
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

    public TaskDto(long id, long executorId, Priority priority, Status status) {
        this.id = id;
        this.executorId = executorId;
        this.priority = priority;
        this.status = status;
    }

    public TaskDto(Task task) {
        this.id = task.getId();
        this.executorId = task.getExecutorId();
        this.priority = task.getPriority();
        this.status = task.getStatus();
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
