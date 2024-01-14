package com.jwt.controller;

import com.jwt.dtos.JwtAuthenticationRespone;
import com.jwt.dtos.RefreshTokenRequest;
import com.jwt.dtos.SignInRequest;
import com.jwt.dtos.SignUpRequest;
import com.jwt.entities.UserEntity;
import com.jwt.services.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationRespone> signin(@RequestBody SignInRequest signIn){
        System.out.println(signIn);
        return ResponseEntity.ok(authenticationService.signin(signIn));
    }
    @PostMapping("/refreshToken")
    public ResponseEntity<JwtAuthenticationRespone> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
