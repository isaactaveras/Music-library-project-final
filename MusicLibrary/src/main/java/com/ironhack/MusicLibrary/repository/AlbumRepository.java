package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
