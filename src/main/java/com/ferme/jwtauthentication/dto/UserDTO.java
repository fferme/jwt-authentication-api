package com.ferme.jwtauthentication.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
    @JsonProperty("_id") UUID id,
    @NotNull String username,
    @NotNull String password, 
    @NotNull @Valid String role,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createDate,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateDate
) {
    
}
