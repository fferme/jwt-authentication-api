package com.ferme.jwtauthentication.dto;

import com.ferme.jwtauthentication.enums.UserRole;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
    @NotNull String username,
    @NotNull String password, 
    @NotNull @Valid UserRole role
) {
    
}
