package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
