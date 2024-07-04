package com.ironhack.MusicLibrary.service;

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

    @BeforeEach
    void setUp() {
        Artist artist = new Artist("Pearl Jam");
        artistRepository.save(artist);

        Genre genre = new Genre("Grunge");
        genreRepository.save(genre);

        Album album = new Album("Ten", 1991, artist, genre);
        albumRepository.save(album);
    }


    @AfterEach
    void tearDown() {
//        albumRepository.deleteAll();
    }

    @Test
    void findById_existingId_albumReturned() {
        Album album = albumRepository.findAll().get(0);
        Album found = albumService.findById(album.getId());
        assertNotNull(found);
        assertEquals("Ten", found.getTitle());
    }
}