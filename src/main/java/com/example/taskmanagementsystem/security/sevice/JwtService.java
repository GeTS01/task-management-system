package com.example.taskmanagementsystem.security.sevice;

import com.example.taskmanagementsystem.security.Token;
import com.example.taskmanagementsystem.security.enums.TokenType;

import java.util.Map;

public interface JwtService {
    Token build(Map<String, Object> claims, TokenType tokenType);

    Map<String, Object> extractAllClaims(String token);

}
