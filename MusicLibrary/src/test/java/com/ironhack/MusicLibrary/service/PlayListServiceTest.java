package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.dtos.PlayListDTO;
import com.ironhack.MusicLibrary.model.*;
import com.ironhack.MusicLibrary.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayListServiceTest {

    @Autowired
    private PlayListService playListService;

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    private Album album;
    private Artist artist;
    private Genre genre;
    private Song song;
    private Song song2;
    private User user;
    private PlayList playList;

    @BeforeEach
    void setUp() {
        artist = new Artist("Pearl Jam");
        artist = artistRepository.save(artist);

        genre = new Genre("Grunge");
        genre = genreRepository.save(genre);

        album = new Album("Ten", 1991, artist, genre);
        album = albumRepository.save(album);

        song = new Song("Black", 346, artist, genre, album);
        song = songRepository.save(song);

        song2 = new Song("Even Flow", 286, artist, genre, album);
        song2 = songRepository.save(song2);

        user = new User("Antonio Ruiz", "antonio", "1234");
        user = userRepository.save(user);

        playList = new PlayList("My songs", Arrays.asList(song, song2), Arrays.asList(user));
        playListRepository.save(playList);
    }

    @AfterEach
    void tearDown() {
        playListRepository.deleteAll();
        songRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        genreRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findById_existingId_playlistReturned() {
        PlayList playList = playListRepository.findAll().getFirst();
        PlayList found = playListService.findById(playList.getId());
        assertNotNull(found);
        assertEquals("My songs", found.getName());
    }

    @Test
    void findById_nonExistingId_throwsNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            playListService.findById(0L);
        });
    }

    @Test
    void create_validPlaylist_playlistSaved() {
        PlayListDTO playListDTO = new PlayListDTO("My favorite songs", Arrays.asList(song, song2), user.getId());
        PlayList saved = playListService.createPlayList(playListDTO);
        assertNotNull(saved);
        assertEquals("My favorite songs", saved.getName());
    }

    @Test
    void create_invalidPlaylistId_throwsNotFound() {
        AlbumDTO albumDTO = new AlbumDTO("Dark Matter", 2024, 0L, 0L);
        PlayListDTO playListDTO = new PlayListDTO("My favorite songs", Arrays.asList(song, song2), 0L);
        assertThrows(ResponseStatusException.class, () -> {
            playListService.createPlayList(playListDTO);
        });
    }

    @Test
    void update_existingId_playlistUpdated() {
        PlayList playList = playListRepository.findAll().getFirst();
        PlayListDTO playListDTO = new PlayListDTO("My songs", Arrays.asList(song, song2), user.getId());
        playListService.updatePlayList(playList.getId(), playListDTO);
        PlayList updated = playListRepository.findById(playList.getId()).get();
        assertEquals("My songs", updated.getName());
    }

    @Test
    void update_nonExistingId_throwsNotFound() {
        PlayListDTO playListDTO = new PlayListDTO("My best songs", Arrays.asList(song, song2), user.getId());
        assertThrows(ResponseStatusException.class, () -> {
            playListService.updatePlayList(0L, playListDTO);
        });
    }

    @Test
    void update_invalidPlaylistId_throwsNotFound() {
        PlayList playList = playListRepository.findAll().get(0);
        playList.setId(999L);
        PlayListDTO playListDTO = new PlayListDTO("My songs", Arrays.asList(song, song2), user.getId());
        assertThrows(ResponseStatusException.class, () -> {
            playListService.updatePlayList(playList.getId(), playListDTO);
        });
    }

    @Test
    void delete_existingId_playlistDeleted() {
        PlayList playList = playListRepository.findAll().getFirst();
        playListService.deletePlayList(playList.getId());
        assertFalse(playListRepository.findById(playList.getId()).isPresent());
    }

    @Test
    void delete_nonExistingId_throwsNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            playListService.deletePlayList(0L);
        });
    }

}