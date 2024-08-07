package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
