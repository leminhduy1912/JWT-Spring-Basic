package com.jwt.services;

import com.jwt.dtos.SignUpRequest;
import com.jwt.dtos.UserDTO;

public interface AuthService {
    UserDTO createUser(SignUpRequest signUp);
}
