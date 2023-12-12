package com.example.taskmanagementsystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends PersistentObject {

    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task taskId;

    public Comment(String text, User userId, Task taskId) {
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
