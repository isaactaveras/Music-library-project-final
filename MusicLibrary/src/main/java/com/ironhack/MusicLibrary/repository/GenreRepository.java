package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
