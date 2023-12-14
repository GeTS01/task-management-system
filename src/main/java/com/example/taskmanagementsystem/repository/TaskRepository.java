package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

     Optional<Task>findByIdAndAuthorId(long id, long authorId);
}
