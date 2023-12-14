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
    @JoinColumn(name = "author_id", nullable = false)
    private User authorId;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task taskId;

    public Comment(String text, User authorId, Task taskId) {
        this.text = text;
        this.authorId = authorId;
        this.taskId = taskId;
    }

    public Comment(){}

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public User getAuthorId() {
        return authorId;
    }
    public void setAuthorId(User authorId) {
        this.authorId = authorId;
    }
    public Task getTaskId() {
        return taskId;
    }
    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }

}
