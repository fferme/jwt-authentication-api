package com.ferme.jwtauthentication.dto;

import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
    @NotNull UUID id,
    @NotNull String username,
    @NotNull String password, 
    @NotNull @Valid String role
) {
    
}
