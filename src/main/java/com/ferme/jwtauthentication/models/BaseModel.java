package com.ferme.jwtauthentication.models;

import java.io.Serializable;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.Getter;

@Data
@MappedSuperclass
public abstract class BaseModel implements Serializable {
    private static final String BR_TIMEZONE = "America/Sao_Paulo";

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    @JsonProperty("_id")
    private UUID id;

    @Getter
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
