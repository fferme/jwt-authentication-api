package com.ferme.jwtauthentication.user.services;

import com.ferme.jwtauthentication.application.exceptions.RecordFieldExistsException;
import com.ferme.jwtauthentication.application.exceptions.RecordNotFoundException;
import com.ferme.jwtauthentication.user.dto.UserDTO;
import com.ferme.jwtauthentication.user.dto.mappers.UserMapper;
import com.ferme.jwtauthentication.user.models.User;
import com.ferme.jwtauthentication.user.repositories.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAll() {
        return this.userMapper.toDTOList(this.userRepository.findAll());
    }

    public UserDTO findById(@NotNull UUID id) {
        return this.userRepository.findById(id).map(this.userMapper::toDTO)
                                  .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public UserDTO create(@Valid @NotNull UserDTO userDTO) {
        if (this.userRepository.findByLogin(userDTO.login()) != null) {
            throw new RecordFieldExistsException("Username", userDTO.login());
        }

        String encryptedPassword = new BCryptPasswordEncoder()
            .encode(userDTO.password());

        User newUser = User.builder()
                           .login(userDTO.login())
                           .password(encryptedPassword)
                           .role(this.userMapper.convertUserRoleValue(userDTO.role()))
                           .build();

        return this.userMapper.toDTO(this.userRepository.save(newUser));
    }

    public UserDTO update(@NotNull UUID id, @Valid @NotNull UserDTO newUserDTO) {
        return this.userRepository.findById(id)
                                  .map(userFound -> {
                                      String encryptedPassword = new BCryptPasswordEncoder()
                                          .encode(newUserDTO.password());

                                      userFound.setLogin(newUserDTO.login());
                                      userFound.setPassword(encryptedPassword);
                                      userFound.setRole(this.userMapper.convertUserRoleValue(newUserDTO.role()));

                                      return this.userMapper.toDTO(this.userRepository.save(userFound));
                                  }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteById(@NotNull UUID id) {
        this.userRepository.delete(this.userRepository.findById(id)
                                                      .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public void deleteAll() {
        this.userRepository.deleteAll();
    }

}