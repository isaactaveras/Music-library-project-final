package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
