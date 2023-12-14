package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.domain.User;
import com.example.taskmanagementsystem.dto.request.CreateUserRegistrationDto;
import com.example.taskmanagementsystem.repository.UserRepository;
import com.example.taskmanagementsystem.security.Token;
import com.example.taskmanagementsystem.security.enums.TokenType;
import com.example.taskmanagementsystem.security.sevice.JwtService;
import com.example.taskmanagementsystem.service.AuthorizeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
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

    /**
     * Метод для авторизации пользователя
     */
    @Override
    public Token authorize(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Неверный логин или пароль")
        );
        if (!passwordEncoder.matches(password,user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Неверный логин или пароль");
        }
        return jwtService.build(
                Map.of(
                        "id", user.getId(),
                        "email", user.getEmail(),
                        "role", user.getRole().name()
                ), TokenType.ACCESS);
    }

    /**
     * Метод для регистрации пользователя
     */
    @Override
    public void registration(CreateUserRegistrationDto createUserRegistrationDto) {
        Optional<User> userOptional = userRepository.findByEmail(createUserRegistrationDto.getEmail());
        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь с такой почтой уже существует");
        }
        User user = new User(
                createUserRegistrationDto.getName(),
                createUserRegistrationDto.getLastName(),
                createUserRegistrationDto.getPatronymic(),
                createUserRegistrationDto.getEmail(),
                createUserRegistrationDto.getPassword(),
                createUserRegistrationDto.getRole()
        );
        user.setCreateAt(ZonedDateTime.now());
        user.setPassword(passwordEncoder.encode(createUserRegistrationDto.getPassword()));
        userRepository.save(user);
    }
}