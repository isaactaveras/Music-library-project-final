package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.SongDTO;
import com.ironhack.MusicLibrary.model.Song;
import com.ironhack.MusicLibrary.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song createSong(SongDTO songDTO) {
        Song song = new Song();
        song.setTitle(songDTO.getTitle());
        song.setDuration(songDTO.getDuration());
        song.setArtist(songDTO.getArtist());
        song.setAlbum(songDTO.getAlbum());
        song.setGenre(songDTO.getGenre());
        return songRepository.save(song);
    }

    public Song updateSong(Long sondId, SongDTO songDTO) {
        Song song = songRepository.findById(sondId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));
        song.setTitle(songDTO.getTitle());
        song.setDuration(songDTO.getDuration());
        song.setArtist(songDTO.getArtist());
        song.setAlbum(songDTO.getAlbum());
        song.setGenre(songDTO.getGenre());
        return songRepository.save(song);
    }

    public Song deleteSong(Long sondId) {
        Song foundSong = songRepository.findById(sondId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));
        songRepository.deleteById(sondId);
        return foundSong;
    }
}
