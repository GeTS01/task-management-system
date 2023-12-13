package com.example.taskmanagementsystem.dto.request;

import com.example.taskmanagementsystem.domain.Comment;
import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

public class CreateCommentDto {
    private String text;
    private User userId;
    private Task taskId;

    public CreateCommentDto(String text, User userId, Task taskId) {
        this.text = text;
        this.userId = userId;
        this.taskId = taskId;
    }

    public Comment create(CreateCommentDto createCommentDto){
        Comment comment = new Comment();
        comment.setText(createCommentDto.getText());
        comment.setTaskId(createCommentDto.taskId);
        comment.setUserId(createCommentDto.userId);
        comment.setCreateAt(ZonedDateTime.now());
        return comment;
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
