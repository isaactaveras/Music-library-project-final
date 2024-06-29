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
public class AlbumDTO {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Year is mandatory")
    private int year;

    @NotNull(message = "Artist is mandatory")
    private Long artistId;

    @NotNull(message = "Genre is mandatory")
    private Long genreId;

}