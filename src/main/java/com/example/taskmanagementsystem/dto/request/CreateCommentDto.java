package com.example.taskmanagementsystem.dto.request;

import com.example.taskmanagementsystem.domain.Comment;

import javax.validation.constraints.NotNull;

public class CreateCommentDto {

    @NotNull(message = "Текст к комментарию не должен быть пустым")
    private String text;
    @NotNull(message = "Укажите идентификатор задачи, к которой хотите оставить комментарий")
    private long taskId;

    public CreateCommentDto(String text, long taskId) {
        this.text = text;
        this.taskId = taskId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
