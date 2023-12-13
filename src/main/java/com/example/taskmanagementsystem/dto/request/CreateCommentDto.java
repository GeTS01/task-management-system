package com.example.taskmanagementsystem.dto.request;

import com.example.taskmanagementsystem.domain.Comment;
import com.example.taskmanagementsystem.domain.Task;

import java.time.ZonedDateTime;

public class CreateCommentDto {
    private String text;
    private Task taskId;

    public CreateCommentDto(String text, Task taskId) {
        this.text = text;
        this.taskId = taskId;
    }

    public Comment buildComment(CreateCommentDto createCommentDto) {
        Comment comment = new Comment();
        comment.setText(createCommentDto.getText());
        comment.setTaskId(createCommentDto.taskId);
        comment.setCreateAt(ZonedDateTime.now());
        return comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }
}
