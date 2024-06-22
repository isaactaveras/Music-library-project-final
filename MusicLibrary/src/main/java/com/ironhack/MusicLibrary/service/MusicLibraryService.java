package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.MusicLibraryDTO;
import com.ironhack.MusicLibrary.model.MusicLibrary;
import com.ironhack.MusicLibrary.repository.MusicLibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MusicLibraryService {

    @Autowired
    private MusicLibraryRepository musicLibraryRepository;

    public MusicLibrary createMusicLibrary(MusicLibraryDTO musicLibraryDTO) {
        MusicLibrary musicLibrary = new MusicLibrary();
        musicLibrary.setUser(musicLibraryDTO.getUser());
        musicLibrary.setSongs(musicLibraryDTO.getSongs());
        musicLibrary.setAlbums(musicLibraryDTO.getAlbums());
        musicLibrary.setGenres(musicLibraryDTO.getGenres());
        musicLibrary.setArtists(musicLibraryDTO.getArtists());
        return musicLibraryRepository.save(musicLibrary);
    }

    public MusicLibrary updateMusicLibrary(Long musicLibraryId, MusicLibraryDTO musicLibraryDTO) {
        MusicLibrary FoundMusicLibrary = musicLibraryRepository.findById(musicLibraryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Music Library Not Found"));
        FoundMusicLibrary.setUser(musicLibraryDTO.getUser());
        FoundMusicLibrary.setSongs(musicLibraryDTO.getSongs());
        FoundMusicLibrary.setAlbums(musicLibraryDTO.getAlbums());
        FoundMusicLibrary.setGenres(musicLibraryDTO.getGenres());
        FoundMusicLibrary.setArtists(musicLibraryDTO.getArtists());
        return musicLibraryRepository.save(FoundMusicLibrary);
    }

    public MusicLibrary deleteMusicLibrary(Long musicLibraryId) {
        MusicLibrary FoundMusicLibrary = musicLibraryRepository.findById(musicLibraryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Music Library Not Found"));
        musicLibraryRepository.deleteById(musicLibraryId);
        return FoundMusicLibrary;
    }
}
