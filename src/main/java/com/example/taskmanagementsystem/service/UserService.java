package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dto.request.CreateUserDto;
import com.example.taskmanagementsystem.dto.response.UserDto;

public interface UserService {
    UserDto read();
    UserDto readById(long id);
}
