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
    private Long artistId;

    @NotNull(message = "Genre is mandatory")
    private Long genreId;

    private List<Song> songs;

    public AlbumDTO(String title, int year, Long artistId, Long genreId) {
        this.title = title;
        this.year = year;
        this.artistId = artistId;
        this.genreId = genreId;
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

    public Long getArtistId() {
        return artistId;
    }

    public void setArtist(Long artistId) {
        this.artistId = artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public void setGenre(Long genreId) {
        this.genreId = genreId;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}