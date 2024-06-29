package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.MusicLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicLibraryRepository extends JpaRepository<MusicLibrary, Long> {
}
