package com.ferme.jwtauthentication.infra.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferme.jwtauthentication.infra.security.dto.AuthenticationDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();

    }
}
