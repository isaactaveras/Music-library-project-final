package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.dtos.SongDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.model.Song;
import com.ironhack.MusicLibrary.repository.AlbumRepository;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
import com.ironhack.MusicLibrary.repository.GenreRepository;
import com.ironhack.MusicLibrary.repository.SongRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SongServiceTest {

    @Autowired
    private SongService songService;

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

    @BeforeEach
    void setUp() {
        artist = new Artist("Pearl Jam");
        artist = artistRepository.save(artist);

        genre = new Genre("Grunge");
        genre = genreRepository.save(genre);

        album = new Album("Ten", 1991, artist, genre);
        album = albumRepository.save(album);

        Song song = new Song("Black", 346, artist, genre, album);
        songRepository.save(song);
    }


    @AfterEach
    void tearDown() {
        songRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    void findById_existingId_songReturned() {
        Song song = songRepository.findAll().getFirst();
        Song found = songService.findById(song.getId());
        assertNotNull(found);
        assertEquals("Black", found.getTitle());
    }

    @Test
    void findById_nonExistingId_throwsNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            songService.findById(0L);
        });
    }

    @Test
    void create_validSong_songSaved() {
        SongDTO songDTO = new SongDTO("Even Flow", 286, artist.getId(), album.getId(), genre.getId());

        Song saved = songService.createSong(songDTO);
        assertNotNull(saved);
        assertEquals("Even Flow", saved.getTitle());
    }

    @Test
    void create_invalidSongId_throwsNotFound() {
        SongDTO songDTO = new SongDTO("Even Flow", 286, 0L, 0L, 0L);
        assertThrows(ResponseStatusException.class, () -> {
            songService.createSong(songDTO);
        });
    }

    @Test
    void update_existingId_songUpdated() {
        SongDTO songDTO = new SongDTO("Jeremy", 319, artist.getId(), album.getId(), genre.getId());
        Song song = songRepository.findAll().get(0);

        songService.updateSong(song.getId(), songDTO);

        Song updated = songRepository.findById(song.getId()).get();
        assertEquals("Jeremy", updated.getTitle());
    }

    @Test
    void update_nonExistingId_throwsNotFound() {
        SongDTO songDTO = new SongDTO("Jeremy", 319, artist.getId(), genre.getId(), album.getId());
        assertThrows(ResponseStatusException.class, () -> {
            songService.updateSong(0L, songDTO);
        });
    }

    @Test
    void update_invalidSongId_throwsNotFound() {
        Song song = songRepository.findAll().getFirst();
        song.setId(999L);
        SongDTO songDTO = new SongDTO("Jeremy", 319, artist.getId(), genre.getId(), album.getId());
        assertThrows(ResponseStatusException.class, () -> {
            songService.updateSong(song.getId(), songDTO);
        });
    }

    @Test
    void delete_existingId_songDeleted() {
        Song song = songRepository.findAll().getFirst();
        songService.deleteSong(song.getId());
        assertFalse(songRepository.findById(song.getId()).isPresent());
    }

    @Test
    void delete_nonExistingId_throwsNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            songService.deleteSong(0L);
        });
    }

}