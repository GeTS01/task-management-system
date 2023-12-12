package com.example.taskmanagementsystem.security.utils;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.security.auth.kerberos.KerberosTicket;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Optional;

public class JwtHelper {
    public static Optional<String> extractJwtFromRequest(HttpServletRequest request){
        final String authHeader = request.getHeader(SecurityConst.PREFIX_AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(SecurityConst.PREFIX_BEARER)){
            return Optional.of(authHeader.substring(SecurityConst.PREFIX_BEARER.length()));
        }
        return Optional.empty();
    }

    public static Key getSignInKey(String key){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }
   private static class SecurityConst {
        public final static String PREFIX_BEARER = "Bearer ";
        public final static String PREFIX_AUTHORIZATION = "Authorization";
    }
}
