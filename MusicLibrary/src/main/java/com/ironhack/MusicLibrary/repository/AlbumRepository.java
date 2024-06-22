package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
