package com.example.taskmanagementsystem.dto.response;

import com.example.taskmanagementsystem.domain.enums.Priority;
import com.example.taskmanagementsystem.domain.enums.Status;

public class TaskDto {

    private long id;
    private long authorId;
    private long executorId;
    private String comment;
    private Priority priority;
    private Status status;

}
