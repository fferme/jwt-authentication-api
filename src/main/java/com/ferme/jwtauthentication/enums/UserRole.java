package com.ferme.jwtauthentication.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum UserRole {
    ADMIN("Admin"),
    GUEST("Guest");

    private String value;
}
