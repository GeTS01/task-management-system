package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.dto.response.UserDto;
import com.example.taskmanagementsystem.repository.UserRepository;
import com.example.taskmanagementsystem.service.AuthorizedUserService;
import com.example.taskmanagementsystem.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final AuthorizedUserService authorizedUserService;
    private final UserRepository repository;

    public UserServiceImpl(AuthorizedUserService authorizedUserService, UserRepository repository) {
        this.authorizedUserService = authorizedUserService;
        this.repository = repository;
    }

    @Override
    public UserDto read() {
        return new UserDto(authorizedUserService.getUser());
    }
}
