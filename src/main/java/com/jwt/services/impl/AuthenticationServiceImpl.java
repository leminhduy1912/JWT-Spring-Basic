package com.jwt.services.impl;


import com.jwt.dtos.JwtAuthenticationRespone;
import com.jwt.dtos.RefreshTokenRequest;
import com.jwt.dtos.SignInRequest;
import com.jwt.dtos.SignUpRequest;
import com.jwt.entities.RoleEntity;
import com.jwt.entities.UserEntity;
import com.jwt.repositories.UserRepository;
import com.jwt.services.IAuthenticationService;
import com.jwt.services.IJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final UserRepository userRepository; // Using constructor injection
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IJWT jwt;

    @Override
    public UserEntity signup(SignUpRequest signUpRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(signUpRequest.getFirstName() + " " + signUpRequest.getLastName());
        userEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userEntity.setEmail(signUpRequest.getEmail());
        userEntity.setRole(RoleEntity.USER);
        return userRepository.save(userEntity);
    }

    @Override
    public JwtAuthenticationRespone signin(SignInRequest signIn) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Invalid Email or Password", e);
        }

        UserEntity user = userRepository.findFirstByEmail(signIn.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password"));

        String jwtToken = jwt.generatedToken(user); // Fixing method name
        String jwtRefreshToken = jwt.generatedRefreshToken(new HashMap<>(), user); // Fixing method name

        JwtAuthenticationRespone jwtAuthenticationResponse = new JwtAuthenticationRespone();
        jwtAuthenticationResponse.setRefreshToken(jwtRefreshToken);
        jwtAuthenticationResponse.setToken(jwtToken);
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationRespone refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String username = jwt.extractUsername(refreshTokenRequest.getToken());
        UserEntity user = userRepository.findFirstByEmail(username).orElseThrow();
        if (jwt.isValidToken(refreshTokenRequest.getToken(),user)){
            String jwtToken = jwt.generatedToken(user);
            JwtAuthenticationRespone jwtAuthenticationResponse = new JwtAuthenticationRespone();
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            jwtAuthenticationResponse.setToken(jwtToken);
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
