package com.ironhack.MusicLibrary.dtos;

import com.ironhack.MusicLibrary.model.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class MusicLibraryDTO {

    @NotNull(message = "User is mandatory")
    private User user;

    private List<Album> albums;

    private List<Song> songs;

    private List<Artist> artists;

    private List<Genre> genres;

    public MusicLibraryDTO() {}

    public MusicLibraryDTO(User user, List<Album> albums, List<Song> songs, List<Artist> artists, List<Genre> genres) {
        this.user = user;
        this.albums = albums;
        this.songs = songs;
        this.artists = artists;
        this.genres = genres;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
