package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.model.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    private Artist artist;
    private Genre genre;
    private Album album;
    private Song song;

    @BeforeEach
    void setUp() {
        artist = new Artist("Black Sabbath");
        artist = artistRepository.save(artist);

        genre = new Genre("Hard Rock");
        genre = genreRepository.save(genre);

        album = new Album("Paranoid", 1970, artist, genre);
        albumRepository.save(album);

        song = new Song("War Pigs", 476, artist, genre, album);
        song = songRepository.save(song);
    }

    @AfterEach
    void tearDown() {
        songRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    void saveSong_newSong_songSaved() {
        Song song = new Song();
        song.setTitle("Paranoid");
        song.setDuration(167);
        song.setGenre(genre);
        song.setAlbum(album);
        song.setArtist(artist);

        Song savedSong = songRepository.save(song);
        assertNotNull(savedSong);
        assertEquals("Paranoid", savedSong.getTitle());
    }
}