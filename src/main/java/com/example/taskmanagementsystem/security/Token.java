package com.example.taskmanagementsystem.security;

import com.example.taskmanagementsystem.security.enums.TokenType;

public record Token(String token, TokenType tokenType) {
}