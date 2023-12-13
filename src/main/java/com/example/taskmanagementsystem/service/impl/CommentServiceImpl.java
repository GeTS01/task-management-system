package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.domain.Comment;
import com.example.taskmanagementsystem.dto.request.CreateCommentDto;
import com.example.taskmanagementsystem.dto.response.CommentDto;
import com.example.taskmanagementsystem.repository.CommentRepository;
import com.example.taskmanagementsystem.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private CommentDto commentDto;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;

    }

    @Override
    public CommentDto create(CreateCommentDto createCommentDto) {
        var comment = createCommentDto.buildComment(createCommentDto);
        commentRepository.save(comment);
        return commentDto.buildCommentDto(comment);
    }

    @Override
    public Optional<List<CommentDto>> findByUserIdAndTaskId(long taskId) {
        var comments = commentRepository.findByUserIdAndTaskId(taskId);
        if (comments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена");
        }
        var getComments = comments.get();
        List<CommentDto> commentList = new ArrayList<>();
        for (var item : getComments) {
            var addList = commentDto.buildCommentDto(item);
            commentList.add(addList);
        }
        return Optional.of(commentList);
    }
}
