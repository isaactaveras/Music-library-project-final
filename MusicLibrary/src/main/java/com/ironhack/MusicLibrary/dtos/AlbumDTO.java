package com.ironhack.MusicLibrary.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO {

    private String title;

    private Integer year;

    private Long artistId;

    private Long genreId;

}