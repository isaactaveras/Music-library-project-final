package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.GenreDTO;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre createGenre(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setName(genreDTO.getName());
        genre.setSongs(genreDTO.getSongs());
        genre.setAlbums(genreDTO.getAlbums());
        return genreRepository.save(genre);
    }

    public Genre updateGenre(Long genreId, GenreDTO genreDTO) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
        genre.setName(genreDTO.getName());
        genre.setSongs(genreDTO.getSongs());
        genre.setAlbums(genreDTO.getAlbums());
        return genreRepository.save(genre);
    }

    public Genre deleteGenre(Long genreId) {
        Genre foundGenre = genreRepository.findById(genreId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
        genreRepository.deleteById(genreId);
        return foundGenre;
    }

}
