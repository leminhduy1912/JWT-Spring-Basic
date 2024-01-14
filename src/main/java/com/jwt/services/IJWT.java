package com.jwt.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface IJWT {
    String generatedToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenExpired(String token);

    boolean isValidToken(String token, UserDetails userDetails);

    String generatedRefreshToken(Map<String,Object> extractClaims, UserDetails userDetails);

}
