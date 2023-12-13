package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.dto.request.CreateCommentDto;
import com.example.taskmanagementsystem.dto.response.CommentDto;
import com.example.taskmanagementsystem.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CreateCommentDto createCommentDto) {
        CommentDto commentDto = commentService.create(createCommentDto);
        return ResponseEntity.ok(commentDto);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByUserIdAndTaskId(@RequestParam("userId") long userId, @RequestParam("taskId") long taskId) {
        Optional<List<CommentDto>> optionalCommentDtoList = commentService.findByUserIdAndTaskId(userId, taskId);
        return optionalCommentDtoList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
