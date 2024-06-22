package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
