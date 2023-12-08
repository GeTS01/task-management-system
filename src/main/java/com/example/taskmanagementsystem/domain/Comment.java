package com.example.taskmanagementsystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends PersistentObject {

    @Column(name = "text")
    private String text;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "task_id")
    private long taskId;

    public Comment(String text, long userId, long taskId) {
        this.text = text;
        this.userId = userId;
        this.taskId = taskId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

}
