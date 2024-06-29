package com.ironhack.MusicLibrary.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public void setArtist(Long artistId) {
        this.artistId = artistId;
    }

    public void setGenre(Long genreId) {
        this.genreId = genreId;
    }

    public void setAlbum(Long albumId) {
        this.albumId = albumId;
    }

}
