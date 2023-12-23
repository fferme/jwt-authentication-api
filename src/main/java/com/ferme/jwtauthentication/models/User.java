package com.ferme.jwtauthentication.models;


import java.sql.Types;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ferme.jwtauthentication.enums.UserRole;
import com.ferme.jwtauthentication.enums.converters.UserRoleConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "tb_user")
@Getter
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    private static final String BR_TIMEZONE = "America/Sao_Paulo";

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    @JsonProperty("_id")
    private UUID id;

    @NotBlank
    @NotNull
    @Column(length = 15, nullable = false)
    private String login;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

    @NotNull
    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = BR_TIMEZONE)
    @Column(updatable = false, nullable = false)
    private final LocalDateTime createDate = LocalDateTime.now(ZoneId.of(BR_TIMEZONE));

    @NotNull
    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = BR_TIMEZONE)
    @Column(nullable = false)
    private LocalDateTime updateDate;

    @PrePersist
    public void initDates() {
        this.updateDate = LocalDateTime.now(ZoneId.of(BR_TIMEZONE));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }@Override

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
