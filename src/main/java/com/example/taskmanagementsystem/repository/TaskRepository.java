package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.enums.Priority;
import com.example.taskmanagementsystem.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {


    @Query(
            value = "UPDATE task SET " +
                    "title = :title, " +
                    "description = :description, " +
                    "priority = :priority, " +
                    "status = :status, " +
                    "executor_id = :executorId " +
                    "WHERE id = :id ",
            nativeQuery = true)
    Task updateById(@Param("title") String title,
                    @Param("description") String description,
                    @Param("priority") Priority priority,
                    @Param("status") Status status,
                    @Param("executorId") Long executorId,
                    @Param("id") Long id);

    @Query(value = "UPDATE task SET " +
            "WHERE id = :id ",
            nativeQuery = true)
    Task updateAuthorById(@Param("id") Long id);

}
