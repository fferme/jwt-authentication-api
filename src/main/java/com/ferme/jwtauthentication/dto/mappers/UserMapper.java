package com.ferme.jwtauthentication.dto.mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ferme.jwtauthentication.dto.UserDTO;
import com.ferme.jwtauthentication.enums.UserRole;
import com.ferme.jwtauthentication.models.User;

@Component
public class UserMapper {
    public List<UserDTO> toDTOList(List<User> users) {
        return (users == null) ? Collections.emptyList()
        : users.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
    }

    public UserDTO toDTO(User user) {
        return (user == null) ? null
        : new UserDTO(
            user.getId(), 
            user.getUsername(), 
            user.getPassword(), 
            user.getRole().getValue(),
            user.getCreateDate(),
            user.getUpdateDate()
        );
    }
    
    public User toEntity(UserDTO userDTO) {
        return (userDTO == null) ? null
        : User.builder()
            .id(userDTO.id())
            .login(userDTO.login())
            .password(userDTO.password())
            .role(convertUserRoleValue(userDTO.role()))
            .build();
    }

    public UserRole convertUserRoleValue(String value) {
        return (value == null) ? null
        : switch(value) {
            case "Owner" -> UserRole.OWNER;
            case "Admin" -> UserRole.ADMIN;
            case "Guest" -> UserRole.GUEST;

            default -> throw new IllegalArgumentException("Grupo de usuário inválido: " + value);
        };
    }
}
