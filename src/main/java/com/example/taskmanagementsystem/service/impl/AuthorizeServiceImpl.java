package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.domain.User;
import com.example.taskmanagementsystem.dto.UserRegistrationDto;
import com.example.taskmanagementsystem.repository.UserRepository;
import com.example.taskmanagementsystem.security.Token;
import com.example.taskmanagementsystem.security.enums.TokenType;
import com.example.taskmanagementsystem.security.sevice.JwtService;
import com.example.taskmanagementsystem.service.AuthorizeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthorizeServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public Token authorize(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Неверный логин или пароль")
        );
        if (!passwordEncoder.matches(password,user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Неверный логин или пароль");
        }
        return jwtService.buildAccess(
                Map.of(
                        "id", user.getId(),
                        "email", user.getEmail(),
                        "role", user.getRole().name()
                ), TokenType.ACCESS);
    }

    @Override
    public void registration(UserRegistrationDto userRegistrationDto) {
        Optional<User> userOptional = userRepository.findByEmail(userRegistrationDto.getEmail());
        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь с такой почтой уже существует");
        }

        User user = userRegistrationDto.createRegistrationUser(userRegistrationDto);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        userRepository.save(user);
    }
}