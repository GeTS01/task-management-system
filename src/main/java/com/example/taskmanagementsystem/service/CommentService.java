package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dto.request.CreateCommentDto;
import com.example.taskmanagementsystem.dto.response.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    CommentDto create(CreateCommentDto createCommentDto);

    Optional<List<CommentDto>> findByUserIdAndTaskId(long userId, long taskId);


}
