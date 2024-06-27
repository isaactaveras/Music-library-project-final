package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.repository.AlbumRepository;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
import com.ironhack.MusicLibrary.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Album createAlbum(AlbumDTO albumDTO) {
        Artist artist = artistRepository.findById(albumDTO.getArtistId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));
        Genre genre = genreRepository.findById(albumDTO.getGenreId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
        Album album = new Album();
        album.setTitle(albumDTO.getTitle());
        album.setYear(albumDTO.getYear());
        album.setArtist(artist);
        album.setGenre(genre);
        return albumRepository.save(album);
    }

    public Album updateAlbum(Long albumId, AlbumDTO albumDTO) {
        Album album = albumRepository.findById(albumId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
        Artist artist = artistRepository.findById(albumDTO.getArtistId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));
        Genre genre = genreRepository.findById(albumDTO.getGenreId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
        album.setTitle(albumDTO.getTitle());
        album.setYear(albumDTO.getYear());
        album.setArtist(artist);
        album.setGenre(genre);
        return albumRepository.save(album);
    }

    public Album deleteAlbum(Long albumId) {
        Album foundAlbum = albumRepository.findById(albumId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
        albumRepository.deleteById(albumId);
        return foundAlbum;
    }
}