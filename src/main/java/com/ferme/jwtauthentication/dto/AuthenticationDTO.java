package com.ferme.jwtauthentication.dto;

public record AuthenticationDTO(
    String login,
    String password
) {}