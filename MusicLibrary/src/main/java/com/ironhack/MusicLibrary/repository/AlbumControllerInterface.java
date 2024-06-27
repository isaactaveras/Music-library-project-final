package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.model.Album;
import jakarta.validation.Valid;

public interface AlbumControllerInterface {
    Album createAlbum(@Valid AlbumDTO albumDTO);
    Album updateAlbum(Long albumId, @Valid AlbumDTO albumDTO);
    Album deleteAlbum(Long albumId);
}
