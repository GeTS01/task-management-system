package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dto.request.CreateCommentDto;
import com.example.taskmanagementsystem.dto.response.CommentDto;

import java.util.List;


public interface CommentService {

    CommentDto create(CreateCommentDto createCommentDto);

    List<CommentDto> readList(long taskId);

}
