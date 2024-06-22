package com.ironhack.MusicLibrary.dtos;

import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Song;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AlbumDTO {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Year is mandatory")
    private int year;

    @NotNull(message = "Artist is mandatory")
    private Artist artist;

    @NotNull(message = "Songs is mandatory")
    private List<Song> songs;

    public AlbumDTO(String title, int year, Artist artist, List<Song> songs) {
        this.title = title;
        this.year = year;
        this.artist = artist;
        this.songs = songs;
    }

    public AlbumDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
