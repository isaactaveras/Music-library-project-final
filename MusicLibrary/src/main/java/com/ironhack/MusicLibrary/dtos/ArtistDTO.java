package com.ironhack.MusicLibrary.dtos;

import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Song;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ArtistDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Songs is mandatory")
    private List<Song> songs;

    @NotNull(message = "Albums is mandatory")
    private List<Album> albums;

    public ArtistDTO() {}

    public ArtistDTO(String name, List<Song> songs, List<Album> albums) {
        this.name = name;
        this.songs = songs;
        this.albums = albums;
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

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
