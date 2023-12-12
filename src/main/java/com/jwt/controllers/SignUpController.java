package com.jwt.controllers;


import com.jwt.dtos.ResponeObject;
import com.jwt.dtos.SignUpRequest;
import com.jwt.dtos.UserDTO;
import com.jwt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<ResponeObject> createUser(@RequestBody SignUpRequest signUpRequest){

        return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("200","Created successfully",authService.createUser(signUpRequest).getName()));
    }
}
