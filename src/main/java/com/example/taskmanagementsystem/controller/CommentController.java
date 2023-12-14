package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.domain.enums.Role;
import com.example.taskmanagementsystem.dto.request.CreateCommentDto;
import com.example.taskmanagementsystem.dto.response.CommentDto;
import com.example.taskmanagementsystem.service.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
@Validated
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @PreAuthorize(Role.CLIENT_NAME)
    public CommentDto create(@RequestBody @Valid CreateCommentDto createCommentDto) {
        return commentService.create(createCommentDto);
    }

    @GetMapping("/list")
    @PreAuthorize(Role.CLIENT_NAME)
    public List<CommentDto> findList(@RequestParam long taskId) {
        return commentService.readList(taskId);
    }
}
