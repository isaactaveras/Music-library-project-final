package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        Genre genre = new Genre();
        genre.setName("Rock");
        genreRepository.save(genre);
    }

    @AfterEach
    void tearDown() {
        genreRepository.deleteAll();
    }

    @Test
    void saveGenre_newGenre_genreSaved() {
        Genre genre = new Genre();
        genre.setName("Rock");
        Genre savedGenre = genreRepository.save(genre);
        assertNotNull(savedGenre);
        assertEquals("Rock", savedGenre.getName());
    }
}