package com.jwt.controllers;

import com.jwt.dtos.AuthenticationRequest;
import com.jwt.dtos.AuthenticationRespone;
import com.jwt.services.jwt.UserDetailService;
import com.jwt.utils.JWTutil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private JWTutil jwt;
    @PostMapping("/authenticate")
    public AuthenticationRespone createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response ) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
    try{

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
    }
    catch(BadCredentialsException e){
        throw  new BadCredentialsException("Incorrect username or password");

    }
    catch (DisabledException disabledException){
        response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not exist,please register first");
        return null;
    }
    final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getEmail());
    final String token = jwt.generateToken(userDetails.getUsername());
    return new AuthenticationRespone(token);
    }
}
