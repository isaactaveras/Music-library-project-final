package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PlayListRepositoryTest {

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private UserRepository userRepository;

    private Artist artist;
    private Genre genre;
    private Album album;
    private Song song;
    private Song song2;
    private User user;
    private PlayList playList;

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

        song2 = new Song("Paranoid", 500, artist, genre, album);
        song2 = songRepository.save(song2);

        user = new User("Antonio Ruiz", "antonio", "1234");
        user = userRepository.save(user);

        playList = new PlayList("My songs", Arrays.asList(song, song2), Arrays.asList(user));
        playListRepository.save(playList);
    }

    @AfterEach
    void tearDown() {
        playListRepository.deleteAll();
        userRepository.deleteAll();
        songRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    void savePlaylist_newPlaylist_playlistSaved() {
        PlayList playlist = new PlayList();
        playlist.setName("My songs");
        playlist.setSongs(Arrays.asList(song, song2));
        playlist.setUsers(Arrays.asList(user));

        PlayList savedPlaylist = playListRepository.save(playlist);
        assertNotNull(savedPlaylist);
        assertEquals("My songs", savedPlaylist.getName());
    }

}