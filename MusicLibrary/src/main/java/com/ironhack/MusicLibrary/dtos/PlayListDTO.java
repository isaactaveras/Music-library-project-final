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

    public PlayListDTO() {}

    public PlayListDTO(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
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
}
