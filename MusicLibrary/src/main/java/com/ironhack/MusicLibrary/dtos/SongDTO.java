package com.ironhack.MusicLibrary.dtos;

import com.ironhack.MusicLibrary.model.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SongDTO {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Duration is mandatory")
    private int duration; // Duration in seconds

    @NotNull(message = "Artist is mandatory")
    private Artist artist;

    @NotNull(message = "Album is mandatory")
    private Album album;

    @NotNull(message = "Genre is mandatory")
    private Genre genre;

    public SongDTO(String title, int duration, Artist artist, Album album, Genre genre) {
        this.title = title;
        this.duration = duration;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public SongDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
