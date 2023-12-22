package com.ferme.jwtauthentication.services;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return userRepository.findAll()
        .stream()
        .map(userMapper::toDTO)
        .sorted(Comparator.comparing(UserDTO::username))
        .collect(Collectors.toList());
    }

    public UserDTO findById(@NotNull UUID id) {
        return userRepository.findById(id).map(userMapper::toDTO)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public UserDTO create(@Valid @NotNull UserDTO userDTO) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    // public UserDTO update(@NotNull UUID id, @Valid @NotNull UserDTO newUserDTO) {
    //     return userRepository.findById(id)
    //         .map(userFound -> {
    //             User user = userMapper.toEntity(newUserDTO);
    //             userFound.setUsername(newUserDTO.username());
    //             userFound.setPassword(newUserDTO.password());
    //             userFound.setRole(userMapper.);

    //             return 
    //         })
    // }
    
}
