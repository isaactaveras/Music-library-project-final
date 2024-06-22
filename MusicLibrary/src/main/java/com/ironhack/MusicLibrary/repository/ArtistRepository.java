package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
