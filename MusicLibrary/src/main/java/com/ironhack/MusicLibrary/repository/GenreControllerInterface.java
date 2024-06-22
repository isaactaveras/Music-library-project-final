package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.dtos.GenreDTO;
import com.ironhack.MusicLibrary.model.Genre;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface GenreControllerInterface {
    Genre createGenre(@Valid GenreDTO genreDTO);
    Genre updateGenre(Long genreId, @Valid GenreDTO genreDTO);
    Genre deleteGenre(Long genreId);
}
