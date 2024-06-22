package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Song;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public Artist createArtist(ArtistDTO artistDTO) {
        Artist artist = new Artist();
        artist.setName(artistDTO.getName());
        artist.setSongs(artistDTO.getSongs());
        artist.setAlbums(artistDTO.getAlbums());
        return artistRepository.save(artist);
    }

    public Artist updateArtist(Long artistId, ArtistDTO artistDTO) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));
        artist.setName(artistDTO.getName());
        artist.setSongs(artistDTO.getSongs());
        artist.setAlbums(artistDTO.getAlbums());
        return artistRepository.save(artist);
    }

    public Artist deleteArtist(Long artistId) {
        Artist foundArtist = artistRepository.findById(artistId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));
        artistRepository.deleteById(artistId);
        return foundArtist;
    }
}
