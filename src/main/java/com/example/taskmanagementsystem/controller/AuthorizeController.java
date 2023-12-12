package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.dto.AuthorizeDto;
import com.example.taskmanagementsystem.security.Token;
import com.example.taskmanagementsystem.service.AuthorizeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user/authorize")
@RestController
@Validated
public class AuthorizeController {
    private final AuthorizeService authorizeService;

    public AuthorizeController(AuthorizeService authorizeService) {
        this.authorizeService = authorizeService;
    }

    @PostMapping
    @ApiOperation(value = "Авторизация пользователя",
            notes = "Метод осуществляет авторизацию пользователя с использованием переданных учетных данных.")
    Token authorize(@RequestBody AuthorizeDto dto) {
        return authorizeService.authorize(dto.getEmail(), dto.getPassword());
    }


}