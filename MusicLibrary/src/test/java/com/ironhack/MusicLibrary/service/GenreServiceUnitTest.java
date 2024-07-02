package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.dtos.GenreDTO;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
import com.ironhack.MusicLibrary.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GenreServiceUnitTest {

    // Creates an instance of the class and injects the mocks into it.
    @InjectMocks
    private GenreService genreService;

    // Creates an instance of the mock
    @Mock
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        // Initializes mocks annotated with @Mock and injects these mocks into the fields annotated with @InjectMocks.
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_existingId_genreReturned() {
        when(genreRepository.findById(1L)).thenReturn(Optional.of(new Genre("Metal")));

        Genre found = genreService.findById(1L);
        assertNotNull(found);
        assertEquals("Metal", found.getName());
    }

    @Test
    void findById_nonExistingId_throwsNotFound() {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> genreService.findById(1L));
    }

    @Test
    void create_validGenre_genreSaved() {
        GenreDTO genreDTO = new GenreDTO("Rock");
        Genre genre = new Genre("Rock");
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre saved = genreService.createGenre(genreDTO);
        assertNotNull(saved);
        assertEquals("Rock", saved.getName());
    }

    @Test
    void update_existingId_genreUpdated() {
        GenreDTO genreDTO = new GenreDTO("Hard Rock");
        Genre genre = new Genre("Hard Rock");

        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        genreService.updateGenre(1L, genreDTO);
        assertEquals("Hard Rock", genre.getName());
    }

    @Test
    void update_nonExistingId_throwsNotFound() {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.empty());
        GenreDTO genreDTO = new GenreDTO("Blues");
        assertThrows(ResponseStatusException.class, () -> genreService.updateGenre(1L, genreDTO));
    }

    @Test
    void delete_existingId_genreDeleted() {
        Genre genre = new Genre();
        genre.setId(1L);
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        doNothing().when(genreRepository).delete(any(Genre.class));

        genreService.deleteGenre(1L);
        verify(genreRepository, times(1)).delete(genre);
    }
}
