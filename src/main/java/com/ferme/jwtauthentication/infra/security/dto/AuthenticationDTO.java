package com.ferme.jwtauthentication.infra.security.dto;

public record AuthenticationDTO(
    String login,
    String password
) {}