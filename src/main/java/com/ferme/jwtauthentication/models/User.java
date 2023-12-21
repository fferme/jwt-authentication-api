package com.ferme.jwtauthentication.models;


import com.ferme.jwtauthentication.enums.UserRole;
import com.ferme.jwtauthentication.enums.converters.UserRoleConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class User extends BaseModel {
    @NotNull
    @Column(nullable = false)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;
}
