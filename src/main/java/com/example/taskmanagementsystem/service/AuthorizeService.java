package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dto.request.CreateUserRegistrationDto;
import com.example.taskmanagementsystem.security.Token;

public interface AuthorizeService {
    Token authorize(String email, String password);
    void registration(CreateUserRegistrationDto createUserRegistrationDto);
}