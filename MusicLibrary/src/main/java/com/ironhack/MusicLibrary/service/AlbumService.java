package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public Album createAlbum(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setTitle(albumDTO.getTitle());
        album.setArtist(albumDTO.getArtist());
        album.setYear(albumDTO.getYear());
        album.setSongs(albumDTO.getSongs());
        return albumRepository.save(album);
    }

    public Album updateAlbum(Long albumId, AlbumDTO albumDTO) {
        Album album = albumRepository.findById(albumId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
        album.setTitle(albumDTO.getTitle());
        album.setArtist(albumDTO.getArtist());
        album.setYear(albumDTO.getYear());
        album.setSongs(albumDTO.getSongs());
        return albumRepository.save(album);
    }

    public Album deleteAlbum(Long albumId) {
        Album foundAlbum = albumRepository.findById(albumId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
        albumRepository.deleteById(albumId);
        return foundAlbum;
    }
}
