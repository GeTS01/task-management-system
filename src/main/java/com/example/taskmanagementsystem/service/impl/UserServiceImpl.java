package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.domain.User;
import com.example.taskmanagementsystem.dto.response.UserDto;
import com.example.taskmanagementsystem.repository.UserRepository;
import com.example.taskmanagementsystem.service.AuthorizedUserService;
import com.example.taskmanagementsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final AuthorizedUserService authorizedUserService;
    private final UserRepository userRepository;

    public UserServiceImpl(AuthorizedUserService authorizedUserService, UserRepository repository) {
        this.authorizedUserService = authorizedUserService;
        this.userRepository = repository;
    }

    /**
     * Метод для получения арторизованного пользованеля
     */
    @Override
    public UserDto read() {
        return new UserDto(authorizedUserService.getUser());
    }

    /**
     * Метод для получения пользованеля по id (идентификатору)
     */
    @Override
    public UserDto readById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден");
        }
        var user = userOptional.get();
        return new UserDto(user);
    }
}
