package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.domain.Comment;
import com.example.taskmanagementsystem.dto.request.CreateCommentDto;
import com.example.taskmanagementsystem.dto.response.CommentDto;
import com.example.taskmanagementsystem.repository.CommentRepository;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.repository.UserRepository;
import com.example.taskmanagementsystem.service.AuthorizedUserService;
import com.example.taskmanagementsystem.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final AuthorizedUserService authorizedUserService;
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(AuthorizedUserService authorizedUserService,
                              CommentRepository commentRepository,
                              TaskRepository taskRepository,
                              UserRepository userRepository) {
        this.authorizedUserService = authorizedUserService;
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    //todo проверить таск айди
    @Override
    public CommentDto create(CreateCommentDto createCommentDto) {
        var authorId = authorizedUserService.getUser().getId();
        var user = userRepository.findById(authorId);
        var task = taskRepository.findById(createCommentDto.getTaskId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Задаче не найдена"));
        Comment comment = new Comment(
                createCommentDto.getText(),
                user.get(),
                task);
        comment.setCreateAt(ZonedDateTime.now());
        commentRepository.save(comment);
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getAuthorId(),
                comment.getTaskId());
    }

    @Override
    public List<CommentDto> readList(long taskId) {
        long authorId = authorizedUserService.getUser().getId();
        List<Comment> comments = commentRepository.findByAuthorIdAndTaskId(authorId, taskId);
        return comments
                .stream()
                .map(CommentDto::new)
                .toList();
    }
}
