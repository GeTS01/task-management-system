package com.example.taskmanagementsystem.dto.response;

import com.example.taskmanagementsystem.domain.Comment;
import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.User;

public class CommentDto {
    private Long id;
    private String text;
    private User userId;
    private Task taskId;

    public CommentDto(Long id, String text, User userId, Task taskId) {
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.taskId = taskId;
    }

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.userId = comment.getAuthorId();
        this.taskId = comment.getTaskId();
    }

    public CommentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }
}
