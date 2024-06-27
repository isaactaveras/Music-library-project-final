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
    private Long artistId;

    @NotNull(message = "Album is mandatory")
    private Long albumId;

    @NotNull(message = "Genre is mandatory")
    private Long genreId;



    public SongDTO(String title, int duration, Long artistId, Long albumId, Long genreId) {
        this.title = title;
        this.duration = duration;
        this.artistId = artistId;
        this.albumId = albumId;
        this.genreId = genreId;
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

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public void setArtist(Long artistId) {
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

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public void setAlbum(Long albumId) {
        this.albumId = albumId;
    }

}
