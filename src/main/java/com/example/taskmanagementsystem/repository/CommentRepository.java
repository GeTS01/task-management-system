package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query(value = "select * from comments c where c.task_id = taskId order by c.id desc ",
            nativeQuery = true)

    Optional<List<Comment>> findByUserIdAndTaskId(@Param("taskId") long taskId);

}
