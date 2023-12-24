package com.ferme.jwtauthentication.infra.security.controllers;

import com.ferme.jwtauthentication.application.exceptions.TokenAuthenticationException;
import com.ferme.jwtauthentication.infra.security.dto.AuthenticationDTO;
import com.ferme.jwtauthentication.infra.security.dto.LoginResponseDTO;
import com.ferme.jwtauthentication.infra.security.services.TokenService;
import com.ferme.jwtauthentication.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(@RequestBody AuthenticationDTO data) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);
            String token = tokenService.generate(((User) auth.getPrincipal()));

            return new LoginResponseDTO(token);
        } catch (AuthenticationException e) {
            throw new TokenAuthenticationException("Error during token authorization process");
        }
    }
}