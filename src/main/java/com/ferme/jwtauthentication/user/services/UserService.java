package com.ferme.jwtauthentication.user.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ferme.jwtauthentication.user.dto.UserDTO;
import com.ferme.jwtauthentication.user.dto.mappers.UserMapper;
import com.ferme.jwtauthentication.application.exceptions.RecordFieldExists;
import com.ferme.jwtauthentication.application.exceptions.RecordNotFoundException;
import com.ferme.jwtauthentication.user.models.User;
import com.ferme.jwtauthentication.user.repositories.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Validated
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAll() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    public UserDTO findById(@NotNull UUID id) {
        return userRepository.findById(id).map(userMapper::toDTO)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public UserDTO create(@Valid @NotNull UserDTO userDTO) {
        if (userRepository.findByLogin(userDTO.login()) != null) {
            throw new RecordFieldExists("Username", userDTO.login());
        }
            
        String encryptedPassword = new BCryptPasswordEncoder()
            .encode(userDTO.password());
            
        User newUser = User.builder()
            .login(userDTO.login())
            .password(encryptedPassword)
            .role(userMapper.convertUserRoleValue(userDTO.role()))
            .build();

        return userMapper.toDTO(userRepository.save(newUser));
    }

    public UserDTO update(@NotNull UUID id, @Valid @NotNull UserDTO newUserDTO) {
        return userRepository.findById(id)
            .map(userFound -> {
                userFound.setLogin(newUserDTO.login());
                userFound.setPassword(newUserDTO.password());
                userFound.setRole(userMapper.convertUserRoleValue(newUserDTO.role()));

                return userMapper.toDTO(userRepository.save(userFound));
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteById(@NotNull UUID id) {
        userRepository.delete(userRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
    
}
