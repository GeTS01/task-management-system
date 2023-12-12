package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.enums.Priority;
import com.example.taskmanagementsystem.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {


    @Query(
            value = "UPDATE task SET " +
                    "title = :title, " +
                    "description = :description, " +
                    "priority = :priority, " +
                    "status = :status, " +
                    "author_id = :authorId, " +
                    "executor_id = :executorId " +
                    "WHERE id = :id ",
            nativeQuery = true)
    Task updateById(@Param("title") String title,
                    @Param("description") String description,
                    @Param("priority") Priority priority,
                    @Param("status") Status status,
                    @Param("authorId") Long authorId,
                    @Param("executorId") Long executorId,
                    @Param("id") Long id);

    @Query(value = "UPDATE task SET " +
            "author_id = :authorId " +
            "WHERE id = :id ",
            nativeQuery = true)
    Task updateAuthorById(@Param("authorId") Long authorId,
                          @Param("id") Long id);




    @Query(value = "SELECT EXISTS (select * from task t where t.id = id)", nativeQuery = true)
    boolean existsById (@Param("id") Long id);

    @Query(value = "select * from task where author_id = :authorId", nativeQuery = true)
    List<Task> getListTaskByAuthorId(@Param("authorId") Long authorId);


}
