package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {
}
