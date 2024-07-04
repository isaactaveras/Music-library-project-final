package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.repository.AlbumRepository;
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
public class AlbumServiceUnitTest {

    // Creates an instance of the class and injects the mocks into it.
    @InjectMocks
    private AlbumService albumService;

    // Creates an instance of the mock
    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        // Initializes mocks annotated with @Mock and injects these mocks into the fields annotated with @InjectMocks.
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_existingId_albumReturned() {
        Album album = new Album();
        album.setTitle("Ten");
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));

        Album found = albumService.findById(1L);
        assertNotNull(found);
        assertEquals("Ten", found.getTitle());
    }

    @Test
    void findById_nonExistingId_throwsNotFound() {
        when(albumRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> albumService.findById(1L));
    }

    @Test
    void create_validAlbum_albumSaved() {
        Artist artist = new Artist();
        artist.setId(1L);
        Genre genre = new Genre();
        genre.setId(1L);
        AlbumDTO albumDTO = new AlbumDTO("Dark Matter", 2024, artist.getId(), genre.getId());
        Album album = new Album("Dark Matter", 2024, artist, genre);

        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(albumRepository.save(any(Album.class))).thenReturn(album);

        Album saved = albumService.createAlbum(albumDTO);
        assertNotNull(saved);
        assertEquals("Dark Matter", saved.getTitle());
    }

    @Test
    void create_invalidAlbumId_throwsNotFound() {
        AlbumDTO albumDTO = new AlbumDTO("Dark Matter", 2024, 0L, 0L);
        when(albumRepository.findById(0L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> albumService.createAlbum(albumDTO));
    }

    @Test
    void update_existingId_albumUpdated() {
        Artist artist = new Artist();
        artist.setId(1L);
        Genre genre = new Genre();
        genre.setId(1L);

        Album album = new Album();
        album.setTitle("Ten");
        album.setId(1L);
        AlbumDTO albumDTO = new AlbumDTO("Dark Matter", 2024, artist.getId(), genre.getId());

        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));
        when(albumRepository.save(any(Album.class))).thenReturn(album);

        albumService.updateAlbum(1L, albumDTO);
        assertEquals("Dark Matter", album.getTitle());
    }

    @Test
    void update_nonExistingId_throwsNotFound() {
        when(albumRepository.findById(anyLong())).thenReturn(Optional.empty());
        AlbumDTO albumDTO = new AlbumDTO("Dark Matter", 2024, 0L, 0L);
        assertThrows(ResponseStatusException.class, () -> albumService.updateAlbum(1L, albumDTO));
    }

    @Test
    void delete_existingId_albumDeleted() {
        Album album = new Album();
        album.setId(1L);
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));
        doNothing().when(albumRepository).deleteById(anyLong());

        albumService.deleteAlbum(1L);
        verify(albumRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_nonExistingId_throwsNotFound() {
        when(albumRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> albumService.deleteAlbum(1L));
    }
}
