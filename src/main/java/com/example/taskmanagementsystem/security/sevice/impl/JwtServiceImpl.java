package com.example.taskmanagementsystem.security.sevice.impl;

import com.example.taskmanagementsystem.security.Token;
import com.example.taskmanagementsystem.security.enums.TokenType;
import com.example.taskmanagementsystem.security.sevice.JwtService;
import com.example.taskmanagementsystem.security.utils.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private final Logger logger = LoggerFactory.getLogger(JwtService.class);
    private final long accessExp;
    private final long refreshExp;


    private final String secret;

//    @Autowired
    public JwtServiceImpl(
                        @Value("${jwt.token.access.expiration}")
                        long accessExp,
                        @Value("${jwt.token.refresh.expiration}")
                        long refreshExp,
                        @Value("${jwt.secret.key}") String secret
    ) {
        this.accessExp = accessExp;
        this.refreshExp = refreshExp;
        this.secret = secret;
    }

    @Override
    public Token buildAccess(Map<String, Object> claims, TokenType tokenType) {
        long exp = (tokenType.equals(TokenType.ACCESS)) ? accessExp : refreshExp;
        String token = Jwts
                .builder()
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(JwtHelper.getSignInKey(secret), SignatureAlgorithm.HS512)
                .compact();
        return new Token(token, tokenType);
    }

    @Override
    public Map<String, Object> extractAllClaims(String token) {
        Map<String, Object> claims = null;
        try {
            claims = Jwts
                    .parserBuilder()
                    .setSigningKey(JwtHelper.getSignInKey(secret)).
                    build()
                    .parseClaimsJwt(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            logger.warn("Token {} was expired", token);
        } catch (MalformedJwtException e) {
            logger.warn("Token {} is MalformedJwt token.", token);
        } catch (SignatureException e) {
            logger.warn("Token {} was signed with another key Signature", token);
        } catch (IllegalArgumentException e) {
            logger.warn("Token {} was IllegalArgument", token);
        } catch (Exception e) {
            logger.warn("Uncaught exception in token {} parsing process", token);
        }
        if (claims == null) {
            return Map.of();
        }
        return claims;
    }
}
