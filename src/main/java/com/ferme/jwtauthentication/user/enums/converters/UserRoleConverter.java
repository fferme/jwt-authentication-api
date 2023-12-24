package com.ferme.jwtauthentication.user.enums.converters;

import java.util.stream.Stream;

import com.ferme.jwtauthentication.user.enums.UserRole;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        return (userRole == null) ? null
        : userRole.getValue();
    }

    @Override
    public UserRole convertToEntityAttribute(String value) {
        return (value == null) ? null
        : Stream.of(UserRole.values())
        .filter(ur -> ur.getValue().equals(value))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }   
    
}
