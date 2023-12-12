package com.jwt.services.impl;

import com.jwt.dtos.SignUpRequest;
import com.jwt.dtos.UserDTO;
import com.jwt.entities.UserEntity;
import com.jwt.repositories.UserRepository;
import com.jwt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignUpRequest signUp) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signUp.getEmail());
        userEntity.setName(signUp.getName());
        userEntity.setPhone(signUp.getPhone());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(signUp.getPassword()));
        System.out.print("password " + userEntity.getPassword());
        UserEntity createdUser = userRepository.save(userEntity);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setPhone(createdUser.getPhone());
        userDTO.setName(createdUser.getName());
        return userDTO;
    }
}
