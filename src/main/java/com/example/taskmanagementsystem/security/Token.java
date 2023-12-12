package com.example.taskmanagementsystem.security;

import com.example.taskmanagementsystem.security.enums.TokenType;

public class Token {
    private final String token;
    private final TokenType tokenType;

    public Token(String token, TokenType tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }
}