package com.ironhack.MusicLibrary.controller;

import com.ironhack.MusicLibrary.dtos.GenreDTO;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.repository.GenreControllerInterface;
import com.ironhack.MusicLibrary.repository.GenreRepository;
import com.ironhack.MusicLibrary.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/genres")
@Validated
public class GenreController implements GenreControllerInterface {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreService genreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre createGenre(@Valid @RequestBody GenreDTO genreDTO) {
        return genreService.createGenre(genreDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Genre updateGenre(@PathVariable(name = "id") Long genreId, @Valid @RequestBody GenreDTO genreDTO) {
        return genreService.updateGenre(genreId, genreDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Genre getGenreById(@PathVariable(name = "id") Long genreId) {
        return genreRepository.findById(genreId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public Genre deleteGenre(@PathVariable(name = "id") Long genreId) {
        return genreService.deleteGenre(genreId);
    }
}
