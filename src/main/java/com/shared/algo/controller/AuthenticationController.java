package com.shared.algo.controller;

import com.shared.algo.model.JwtRequest;
import com.shared.algo.model.JwtResponse;
import com.shared.algo.service.UserService;
import com.shared.algo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class AuthenticationController {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", badCredentialsException);
        }

        UserDetails userDetail = userService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtils.generateToken(userDetail);
        return new JwtResponse(token);
    }
}
