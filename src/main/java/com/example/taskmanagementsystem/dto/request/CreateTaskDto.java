package com.example.taskmanagementsystem.dto.request;

import com.example.taskmanagementsystem.domain.enums.Priority;
import com.example.taskmanagementsystem.domain.enums.Status;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CreateTaskDto {

    @NotNull(message = "Поле заголовок не должно быть пустым")
    private String title;
    private String description;
    @NotNull(message = "Поле приоритетности не дожно быть пустым")
    private Priority priority;
    @NotNull(message = "Поле статус не должно быть пустым")
    private Status status;
    @NotNull(message = "Почта не должна быть пустой пустое")
    @Email
    private String email;
    
    public CreateTaskDto(String title,
                         String description,
                         Priority priority,
                         Status status,
                         String email) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.email = email;
    }

    public CreateTaskDto() {
    }

    public CreateTaskDto(CreateTaskDto createTaskDto){
        this.title = createTaskDto.getTitle();
        this.description = createTaskDto.getDescription();
        this.priority = createTaskDto.getPriority();
        this.status = createTaskDto.getStatus();
        this.email = createTaskDto.email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
