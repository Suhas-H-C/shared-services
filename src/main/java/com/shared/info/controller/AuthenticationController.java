package com.shared.info.controller;

import com.shared.info.dto.JwtRequest;
import com.shared.info.dto.JwtResponse;
import com.shared.info.utils.JWTUtils;
import com.shared.info.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public final class AuthenticationController {

    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserService service;

    @PostMapping(value = "/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.username(), jwtRequest.password()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", badCredentialsException);
        }
        UserDetails userDetail = service.loadUserByUsername(jwtRequest.username());
        String token = jwtUtils.generateToken(userDetail);
        return JwtResponse.builder().jwtToken(token).build();
    }
}
