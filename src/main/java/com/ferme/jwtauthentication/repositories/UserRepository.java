package com.ferme.jwtauthentication.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ferme.jwtauthentication.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    
}
