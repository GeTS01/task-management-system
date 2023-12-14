package com.example.taskmanagementsystem.security.filter;

import com.example.taskmanagementsystem.domain.User;
import com.example.taskmanagementsystem.repository.UserRepository;
import com.example.taskmanagementsystem.security.AuthorizedUser;
import com.example.taskmanagementsystem.security.sevice.JwtService;
import com.example.taskmanagementsystem.security.utils.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Optional<String> tokenOpt = JwtHelper.extractJwtFromRequest(request);
        if (tokenOpt.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = tokenOpt.get();
        Map<String, Object> claims = jwtService.extractAllClaims(token);
        if (claims.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        Integer userId = (Integer) claims.get("id");
        Optional<User> userOpt = userRepository.findById(userId.longValue());
        if (userOpt.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        User user = userOpt.get();
        AuthorizedUser authorizedUser = new AuthorizedUser(
                user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().name())), user.getId()
        );
        SecurityContextHolder.getContext().setAuthentication(authorizedUser);
        filterChain.doFilter(request,response);
    }
}
