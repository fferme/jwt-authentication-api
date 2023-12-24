package com.ferme.jwtauthentication.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
    
@AllArgsConstructor
@Getter
public enum UserRole {
    OWNER("Owner"),
    ADMIN("Admin"),
    GUEST("Guest");

    private String value;
    
    @Override
    public String toString() {
        return value;
    }
}
