package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.domain.User;
import com.example.taskmanagementsystem.repository.UserRepository;
import com.example.taskmanagementsystem.security.AuthorizedUser;
import com.example.taskmanagementsystem.service.AuthorizedUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizedUserServiceImpl implements AuthorizedUserService {
    private final UserRepository userRepository;

    public AuthorizedUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser() {
        AuthorizedUser authorizedUser = (AuthorizedUser) SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmailAndPassword(authorizedUser.getUsername(), authorizedUser.getPassword()).get();
    }
}
