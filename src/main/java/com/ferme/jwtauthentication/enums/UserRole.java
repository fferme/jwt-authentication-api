package com.ferme.jwtauthentication.enums;

import lombok.ToString;

@ToString
public enum UserRole {
    OWNER("Owner"),
    ADMIN("Admin"),
    GUEST("Guest");

    private String value;

    private UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
