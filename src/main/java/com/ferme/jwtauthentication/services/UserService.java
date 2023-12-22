package com.ferme.jwtauthentication.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ferme.jwtauthentication.dto.UserDTO;
import com.ferme.jwtauthentication.dto.mappers.UserMapper;
import com.ferme.jwtauthentication.exceptions.RecordNotFoundException;
import com.ferme.jwtauthentication.repositories.UserRepository;

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
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    public UserDTO update(@NotNull UUID id, @Valid @NotNull UserDTO newUserDTO) {
        return userRepository.findById(id)
            .map(userFound -> {
                userFound.setUsername(newUserDTO.username());
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
