package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    private Artist artist;
    private Genre genre;
    private Album album;

    @BeforeEach
    void setUp() {
        artist = new Artist("Black Sabbath");
        artist = artistRepository.save(artist);

        genre = new Genre("Hard Rock");
        genre = genreRepository.save(genre);

        album = new Album("Paranoid", 1970, artist, genre);
        albumRepository.save(album);
    }

    @AfterEach
    void tearDown() {
        albumRepository.deleteAll();
    }

    @Test
    void saveAlbum_newAlbum_albumSaved() {
        Album album = new Album();
        album.setTitle("Black Sabbath");
        album.setYear(1970);

        Album savedAlbum = albumRepository.save(album);
        assertNotNull(savedAlbum);
        assertEquals("Black Sabbath", savedAlbum.getTitle());
    }

}