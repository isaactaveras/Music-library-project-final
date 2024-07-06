package com.ironhack.MusicLibrary.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO {

    private String title;

    private Integer duration; // Duration in seconds

    private Long artistId;

    private Long albumId;

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
