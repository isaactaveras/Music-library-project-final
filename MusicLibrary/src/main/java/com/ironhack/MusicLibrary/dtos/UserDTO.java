package com.ironhack.MusicLibrary.dtos;

import com.ironhack.MusicLibrary.model.PlayList;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UserDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Name is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    public UserDTO() {}

    public UserDTO(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
