package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.dtos.GenreDTO;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
import com.ironhack.MusicLibrary.repository.GenreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenreServiceTest {

    @Autowired
    private GenreService genreService;

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
    void findById_existingId_genreReturned() {
        Genre genre = genreRepository.findAll().get(0);
        Genre found = genreService.findById(genre.getId());
        assertNotNull(found);
        assertEquals("Rock", found.getName());
    }

    @Test
    void findById_nonExistingId_throwsNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            genreService.findById(0L);
        });
    }

    @Test
    void create_validGenre_genreSaved() {
        GenreDTO genreDTO = new GenreDTO("Pop Rock");
        Genre saved = genreService.createGenre(genreDTO);
        assertNotNull(saved);
        assertEquals("Pop Rock", saved.getName());
    }

    @Test
    void update_existingId_genreUpdated() {
        Genre genre = genreRepository.findAll().get(0);
        GenreDTO genreDTO = new GenreDTO("Nu metal");
        genreService.updateGenre(genre.getId(), genreDTO);
        Genre updated = genreRepository.findById(genre.getId()).get();
        assertEquals("Nu metal", updated.getName());
    }

    @Test
    void update_nonExistingId_throwsNotFound() {
        GenreDTO genreDTO = new GenreDTO("Pop");
        assertThrows(ResponseStatusException.class, () -> {
            genreService.updateGenre(0L, genreDTO);
        });
    }

    @Test
    void delete_existingId_genreDeleted() {
        Genre genre = genreRepository.findAll().get(0);
        genreService.deleteGenre(genre.getId());
        assertFalse(genreRepository.findById(genre.getId()).isPresent());
    }

    @Test
    void delete_nonExistingId_throwsNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            genreService.deleteGenre(0L);
        });
    }

    @Test
    void findById_existingId_returnsGenre() {
        Genre genre = genreRepository.findAll().get(0);
        Genre found = genreService.findById(genre.getId());
        assertNotNull(found);
        assertEquals("Rock", found.getName());
    }

    @Test
    void update_existingId_updatesGenre() {
        Genre genre = genreRepository.findAll().get(0);
        GenreDTO genreDTO = new GenreDTO("Heavy Metal");
        genreService.updateGenre(genre.getId(), genreDTO);
        Genre updated = genreRepository.findById(genre.getId()).get();
        assertEquals("Heavy Metal", updated.getName());
    }

    @Test
    void delete_existingId_deletesGenre() {
        Genre genre = genreRepository.findAll().get(0);
        genreService.deleteGenre(genre.getId());
        assertFalse(genreRepository.findById(genre.getId()).isPresent());
    }

}