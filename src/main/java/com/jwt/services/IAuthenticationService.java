package com.jwt.services;

import com.jwt.dtos.JwtAuthenticationRespone;
import com.jwt.dtos.RefreshTokenRequest;
import com.jwt.dtos.SignInRequest;
import com.jwt.dtos.SignUpRequest;
import com.jwt.entities.UserEntity;

public interface IAuthenticationService {
    UserEntity signup(SignUpRequest signUpRequest);
    JwtAuthenticationRespone signin(SignInRequest signIn);
    JwtAuthenticationRespone refreshToken(RefreshTokenRequest refreshTokenRequest);
}
