package com.ferme.jwtauthentication.repositories;


import java.sql.Types;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

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
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "tb_user")
@Entity
public class User {
    private static final String BR_TIMEZONE = "America/Sao_Paulo";

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    @JsonProperty("_id")
    private UUID id;

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

    @Getter
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = BR_TIMEZONE)
    @Column(updatable = false, nullable = false)
    private final LocalDateTime createDate = LocalDateTime.now(ZoneId.of(BR_TIMEZONE));

    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = BR_TIMEZONE)
    @Column(nullable = false)
    private LocalDateTime updateDate;

    @PrePersist
    public void initDates() {
        this.updateDate = LocalDateTime.now(ZoneId.of(BR_TIMEZONE));
    }
}
