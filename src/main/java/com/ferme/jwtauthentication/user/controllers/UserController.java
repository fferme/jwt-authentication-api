package com.ferme.jwtauthentication.user.controllers;

import com.ferme.jwtauthentication.user.dto.UserDTO;
import com.ferme.jwtauthentication.user.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/auth/users")
@Transactional
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable @NotNull UUID id) {
        return this.userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserDTO userDTO) {
        return this.userService.create(userDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable @NotNull UUID id, @RequestBody @Valid @NotNull UserDTO newUserDTO) {
        return this.userService.update(id, newUserDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @NotNull UUID id) {
        this.userService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        this.userService.deleteAll();
    }
}