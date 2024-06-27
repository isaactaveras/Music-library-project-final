package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.dtos.SongDTO;
import com.ironhack.MusicLibrary.model.Song;
import jakarta.validation.Valid;

public interface SongControllerInterface {
    Song createSong(@Valid SongDTO songDTO);
    Song updateSong(Long songId, @Valid SongDTO songDTO);
    Song deleteSong(Long songId);
}
