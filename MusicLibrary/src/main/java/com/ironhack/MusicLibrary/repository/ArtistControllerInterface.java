package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.model.Artist;
import jakarta.validation.Valid;

public interface ArtistControllerInterface {
    Artist createArtist(@Valid ArtistDTO artistDTO);
    Artist updateArtist(Long artistId, @Valid ArtistDTO artistDTO);
    Artist deleteArtist(Long artistId);
}
