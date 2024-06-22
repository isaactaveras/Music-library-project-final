package com.ironhack.MusicLibrary.dtos;

import com.ironhack.MusicLibrary.model.Song;
import com.ironhack.MusicLibrary.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PlayListDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Songs is mandatory")
    private List<Song> songs;

    @NotNull(message = "User is mandatory")
    private User user;

    public PlayListDTO() {}

    public PlayListDTO(String name, List<Song> songs, User user) {
        this.name = name;
        this.songs = songs;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
