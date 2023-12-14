package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query(value = "select * from comments c where c.author_id = 1 and c.task_id = 2 ",
            nativeQuery = true)
    List<Comment> findByAuthorIdAndTaskId(@Param("authorId") long authorId, @Param("taskId") long taskId);

}
