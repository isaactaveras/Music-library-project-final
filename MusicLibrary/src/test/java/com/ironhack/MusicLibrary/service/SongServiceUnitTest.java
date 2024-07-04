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
public class SongServiceUnitTest {

    // Creates an instance of the class and injects the mocks into it.
    @InjectMocks
    private SongService songService;

    // Creates an instance of the mock
    @Mock
    private SongRepository songRepository;

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
    void findById_existingId_songReturned() {
        Song song = new Song();
        song.setTitle("Black");
        when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        Song found = songService.findById(1L);
        assertNotNull(found);
        assertEquals("Black", found.getTitle());
    }

    @Test
    void findById_nonExistingId_throwsNotFound() {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> songService.findById(1L));
    }

    @Test
    void create_validSong_songSaved() {
        Artist artist = new Artist();
        artist.setId(1L);
        Genre genre = new Genre();
        genre.setId(1L);
        Album album = new Album();
        album.setId(1L);

        SongDTO songDTO = new SongDTO("Even Flow", 286, artist.getId(), genre.getId(), album.getId());
        Song song = new Song("Even Flow", 286, artist, genre, album);

        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));
        when(songRepository.save(any(Song.class))).thenReturn(song);

        Song saved = songService.createSong(songDTO);
        assertNotNull(saved);
        assertEquals("Even Flow", saved.getTitle());
    }

    @Test
    void create_invalidSongId_throwsNotFound() {
        SongDTO songDTO = new SongDTO("Black", 300, 0L, 0L, 0L);
        when(songRepository.findById(0L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> songService.createSong(songDTO));
    }

    @Test
    void update_existingId_songUpdated() {
        Artist artist = new Artist();
        artist.setId(1L);
        Genre genre = new Genre();
        genre.setId(1L);
        Album album = new Album();
        album.setId(1L);

        Song song = new Song();
        song.setTitle("Release");
        song.setId(1L);
        SongDTO songDTO = new SongDTO("Jeremy", 380, artist.getId(), genre.getId(), album.getId());

        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));
        when(songRepository.findById(1L)).thenReturn(Optional.of(song));
        when(songRepository.save(any(Song.class))).thenReturn(song);

        songService.updateSong(1L, songDTO);
        assertEquals("Jeremy", song.getTitle());
    }

    @Test
    void update_nonExistingId_throwsNotFound() {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());
        SongDTO songDTO = new SongDTO("Black", 300, 0L, 0L, 0L);
        assertThrows(ResponseStatusException.class, () -> songService.updateSong(1L, songDTO));
    }

    @Test
    void delete_existingId_songDeleted() {
        Song song = new Song();
        song.setId(1L);
        when(songRepository.findById(1L)).thenReturn(Optional.of(song));
        doNothing().when(songRepository).deleteById(anyLong());

        songService.deleteSong(1L);
        verify(songRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_nonExistingId_throwsNotFound() {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> songService.deleteSong(1L));
    }
}
