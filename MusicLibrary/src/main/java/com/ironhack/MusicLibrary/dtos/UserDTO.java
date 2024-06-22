package com.ironhack.MusicLibrary.dtos;

import com.ironhack.MusicLibrary.model.PlayList;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UserDTO {

    @NotBlank(message = "Name is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;


    private List<PlayList> playlists;

    public UserDTO() {}

    public UserDTO(String username, String password, List<PlayList> playlists) {
        this.username = username;
        this.password = password;
        this.playlists = playlists;
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

    public List<PlayList> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlayList> playlists) {
        this.playlists = playlists;
    }
}
