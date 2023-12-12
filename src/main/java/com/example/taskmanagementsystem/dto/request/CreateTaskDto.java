package com.example.taskmanagementsystem.dto.request;

import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.enums.Priority;
import com.example.taskmanagementsystem.domain.enums.Status;
import com.example.taskmanagementsystem.dto.UserRegistrationDto;
import com.example.taskmanagementsystem.dto.response.TaskDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class CreateTaskDto {

    @NotNull
    @Length(min = 3, max = 10)
    private String title;

    private String description;

    @NotNull
    private Priority priority;

    @NotNull
    private Status status;

    @NotNull
    private long authorId;

    @NotNull
    private long executorId;


    public CreateTaskDto(String title, String description,
                         Priority priority, Status status,
                         long authorId, long executorId) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.authorId = authorId;
        this.executorId = executorId;
    }

    public Task CreateTaskDto(CreateTaskDto create){
        Task task = new Task();
        task.setTitle(create.getTitle());
        task.setDescription(create.getDescription());
        task.setPriority(create.getPriority());
        task.setStatus(create.getStatus());
        task.setAuthorId(create.getAuthorId());
        task.setExecutorId(create.getExecutorId());
        task.setCreateAt(ZonedDateTime.now());
        return task;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(long executorId) {
        this.executorId = executorId;
    }

}
