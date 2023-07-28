package com.intela.ecommerce.util;

import com.intela.ecommerce.models.User;
import com.intela.ecommerce.repositories.UserRepository;
import com.intela.ecommerce.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class Util {
    private final UserRepository userRepository;

    public static User getUserByToken(HttpServletRequest request, JwtService jwtService, UserRepository userRepository) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String userEmail;
        final String jwtToken;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Please enter a valid token");
        }

        jwtToken = authHeader.split(" ")[1].trim();
        userEmail = jwtService.extractUsername(jwtToken);

        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
