package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.dto.request.CreateCommentDto;
import com.example.taskmanagementsystem.dto.response.CommentDto;
import com.example.taskmanagementsystem.repository.CommentRepository;
import com.example.taskmanagementsystem.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto create(CreateCommentDto createCommentDto) {
        var comment = createCommentDto.create(createCommentDto);
        commentRepository.save(comment);

        return new CommentDto();
    }

    @Override
    public Optional<List<CommentDto>> findByUserIdAndTaskId(long userId, long taskId) {
        return Optional.empty();
    }
}
