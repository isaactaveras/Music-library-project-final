package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.UserDTO;
import com.ironhack.MusicLibrary.model.User;
import com.ironhack.MusicLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPlaylists(userDTO.getPlaylists());
        return userRepository.save(user);
    }

    public User updateUser(Long userId, UserDTO userDTO) {
        User foundUser = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        foundUser.setPlaylists(userDTO.getPlaylists());

        if(userDTO.getUsername() != null && !userDTO.getUsername().equals("")){
            foundUser.setUsername(userDTO.getUsername());
        }
        if(userDTO.getPassword() != null && !userDTO.getPassword().equals("")){
            foundUser.setPassword(userDTO.getPassword());
        }
        return userRepository.save(foundUser);
    }

    public User deleteUser(Long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userRepository.deleteById(userId);
        return foundUser;
    }
}
