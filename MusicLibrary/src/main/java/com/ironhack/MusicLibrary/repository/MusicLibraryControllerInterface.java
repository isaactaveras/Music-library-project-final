package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.dtos.MusicLibraryDTO;
import com.ironhack.MusicLibrary.model.MusicLibrary;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface MusicLibraryControllerInterface {
    MusicLibrary createMusicLibrary(@Valid MusicLibraryDTO musicLibraryDTO);
    MusicLibrary updateMusicLibrary(Long musicLibraryId, @Valid MusicLibraryDTO musicLibraryDTO);
    MusicLibrary deleteMusicLibrary(Long musicLibraryId);
}
