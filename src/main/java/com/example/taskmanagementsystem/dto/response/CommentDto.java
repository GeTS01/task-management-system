package com.example.taskmanagementsystem.dto.response;

import com.example.taskmanagementsystem.domain.Comment;
import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.User;

public class CommentDto {
    private Long id;
    private String text;
    private Task taskId;

    public CommentDto(Long id, String text, User userId, Task taskId) {
        this.id = id;
        this.text = text;
        this.taskId = taskId;
    }

    public CommentDto() {
    }

    public CommentDto buildCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentDto.getId());
        commentDto.setText(comment.getText());
        commentDto.setTaskId(comment.getTaskId());
        return commentDto;
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

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }
}
