package com.ironhack.MusicLibrary.dtos;

import com.ironhack.MusicLibrary.model.Song;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayListDTO {

    private String name;

    private List<Song> songs = new ArrayList<>();

    private Long userId;
}
