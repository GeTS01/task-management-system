package com.example.taskmanagementsystem.domain;

import com.example.taskmanagementsystem.domain.enums.Priority;
import com.example.taskmanagementsystem.domain.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task extends PersistentObject {

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "priority")
    private Priority priority;
    @Column(name = "status")
    private Status status;
    @Column(name = "author_id")
    private long authorId;
    @Column(name = "executor_id")
    private long executorId;

    public Task(
            String title,
            String description,
            Priority priority,
            Status status,
            long authorId,
            long executorId) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.authorId = authorId;
        this.executorId = executorId;
    }

    public Task() {
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
