package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
