package com.ferme.jwtauthentication.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ferme.jwtauthentication.enums.UserRole;
import com.ferme.jwtauthentication.enums.validation.ValueOfEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
    @JsonProperty("_id") UUID id,
    @NotBlank @NotNull String username,
    @NotBlank @NotNull String password, 
    @NotNull @ValueOfEnum(enumClass = UserRole.class) String role,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createDate,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateDate
) {
    
}
