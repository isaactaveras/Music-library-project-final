package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.repository.AlbumRepository;
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
class AlbumServiceTest {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    private Artist artist;
    private Genre genre;

    @BeforeEach
    void setUp() {
        artist = new Artist("Pearl Jam");
        artistRepository.save(artist);

        genre = new Genre("Grunge");
        genreRepository.save(genre);

        Album album = new Album("Ten", 1991, artist, genre);
        albumRepository.save(album);
    }


    @AfterEach
    void tearDown() {
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    void findById_existingId_albumReturned() {
        Album album = albumRepository.findAll().getFirst();
        Album found = albumService.findById(album.getId());
        assertNotNull(found);
        assertEquals("Ten", found.getTitle());
    }

    @Test
    void findById_nonExistingId_throwsNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            albumService.findById(0L);
        });
    }

    @Test
    void create_validAlbum_albumSaved() {
        AlbumDTO albumDTO = new AlbumDTO("Dark Matter", 2024, artist.getId(), genre.getId());
        Album saved = albumService.createAlbum(albumDTO);
        assertNotNull(saved);
        assertEquals("Dark Matter", saved.getTitle());
    }

    @Test
    void create_invalidAlbumId_throwsNotFound() {
        AlbumDTO albumDTO = new AlbumDTO("Dark Matter", 2024, 0L, 0L);
        assertThrows(ResponseStatusException.class, () -> {
            albumService.createAlbum(albumDTO);
        });
    }

    @Test
    void update_existingId_albumUpdated() {
        Album album = albumRepository.findAll().getFirst();
        AlbumDTO albumDTO = new AlbumDTO("Vitalogy", 1994, artist.getId(), genre.getId());
        albumService.updateAlbum(album.getId(), albumDTO);
        Album updated = albumRepository.findById(album.getId()).get();
        assertEquals("Vitalogy", updated.getTitle());
    }

    @Test
    void update_nonExistingId_throwsNotFound() {
        AlbumDTO albumDTO = new AlbumDTO("Pearl Jam", 2006, artist.getId(), genre.getId());
        assertThrows(ResponseStatusException.class, () -> {
            albumService.updateAlbum(0L, albumDTO);
        });
    }

    @Test
    void update_invalidAlbumId_throwsNotFound() {
        Album album = albumRepository.findAll().get(0);
        album.setId(999L);
        AlbumDTO albumDTO = new AlbumDTO("Pearl Jam", 2006, artist.getId(), genre.getId());
        assertThrows(ResponseStatusException.class, () -> {
            albumService.updateAlbum(album.getId(), albumDTO);
        });
    }

    @Test
    void delete_existingId_albumDeleted() {
        Album album = albumRepository.findAll().get(0);
        albumService.deleteAlbum(album.getId());
        assertFalse(albumRepository.findById(album.getId()).isPresent());
    }

    @Test
    void delete_nonExistingId_throwsNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            albumService.deleteAlbum(0L);
        });
    }
}