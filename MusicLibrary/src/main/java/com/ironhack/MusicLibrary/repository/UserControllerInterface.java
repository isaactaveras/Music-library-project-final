package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.dtos.UserDTO;
import com.ironhack.MusicLibrary.model.User;
import jakarta.validation.Valid;

public interface UserControllerInterface {
    User createUser(@Valid UserDTO userDTO);
    User updateUser(Long userId, @Valid UserDTO userDTO);
    User deleteUser(Long userId);
}
