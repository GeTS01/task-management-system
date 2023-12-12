package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {



}
