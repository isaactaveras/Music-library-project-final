package com.ironhack.MusicLibrary.dtos;

import com.ironhack.MusicLibrary.model.Song;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayListDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Songs is mandatory")
    private List<Song> songs;
}
