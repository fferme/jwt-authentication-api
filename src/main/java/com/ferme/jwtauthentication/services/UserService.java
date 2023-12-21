package com.ferme.jwtauthentication.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ferme.jwtauthentication.repositories.UserRepository;

@Validated
@Service
public class UserService {
    private final UserRepository userRepository;
    
}
