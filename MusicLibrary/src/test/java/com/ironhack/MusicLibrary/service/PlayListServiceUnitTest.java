package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.PlayListDTO;
import com.ironhack.MusicLibrary.model.*;
import com.ironhack.MusicLibrary.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PlayListServiceUnitTest {

    // Creates an instance of the class and injects the mocks into it.
    @InjectMocks
    private PlayListService playListService;

    // Creates an instance of the mock
    @Mock
    private PlayListRepository playListRepository;

    @Mock
    private SongRepository songRepository;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // Initializes mocks annotated with @Mock and injects these mocks into the fields annotated with @InjectMocks.
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_existingId_playlistReturned() {
        PlayList playList = new PlayList();
        playList.setName("My songs");
        when(playListRepository.findById(1L)).thenReturn(Optional.of(playList));

        PlayList found = playListService.findById(1L);
        assertNotNull(found);
        assertEquals("My songs", found.getName());
    }

    @Test
    void findById_nonExistingId_throwsNotFound() {
        when(playListRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> playListService.findById(1L));
    }

//    @Test
//    void create_validPlaylist_playlistSaved() {
//        Artist artist = new Artist();
//        artist.setId(1L);
//        Genre genre = new Genre();
//        genre.setId(1L);
//        Album album = new Album();
//        album.setId(1L);
//        Song song1 = new Song("Even Flow", 286, artist, genre, album);
//        song1.setId(1L);
//        Song song2 = new Song("Black", 350, artist, genre, album);
//        song2.setId(2L);
//        List<Song> songs = Arrays.asList(song1, song2);
//        User user = new User("Pedro Perez", "pedro", "1234");
//        user.setId(1L);
//
//        PlayListDTO playListDTO = new PlayListDTO("My favorite songs", songs, user.getId());
//        PlayList playList = new PlayList("My songs", songs, Arrays.asList(user));
//
//        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
//        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
//        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));
//        when(songRepository.findAllById(Arrays.asList(1L,2L))).thenReturn(songs);
//        when(userRepository.findById(1L)).thenReturn(user);
//        when(playListRepository.findById(1L)).thenReturn(Optional.of(playList));
//        when(playListRepository.save(any(PlayList.class))).thenReturn(playList);
//
//        PlayList saved = playListService.createPlayList(playListDTO);
//        assertNotNull(saved);
//        assertEquals("My favorite songs", saved.getName());
//    }

    @Test
    void create_invalidPlaylistId_throwsNotFound() {
        PlayListDTO playListDTO = new PlayListDTO("My favorite songs", Arrays.asList(), 0L);
        when(playListRepository.findById(0L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> playListService.createPlayList(playListDTO));
    }

    @Test
    void update_existingId_playlistUpdated() {
        Artist artist = new Artist();
        artist.setId(1L);
        Genre genre = new Genre();
        genre.setId(1L);
        Album album = new Album();
        album.setId(1L);
        Song song1 = new Song("Even Flow", 286, artist, genre, album);
        song1.setId(1L);
        Song song2 = new Song("Black", 350, artist, genre, album);
        song2.setId(2L);
        List<Song> songs = Arrays.asList(song1, song2);
        User user = new User("Pedro Perez", "pedro", "1234");
        user.setId(1L);

        PlayList playList = new PlayList();
        playList.setName("My songs");
        playList.setId(1L);
        PlayListDTO playListDTO = new PlayListDTO("My favorite songs", songs, user.getId());

        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));
        when(songRepository.findAllById(Arrays.asList(1L,2L))).thenReturn(songs);
        when(userRepository.findById(1L)).thenReturn(user);
        when(playListRepository.findById(1L)).thenReturn(Optional.of(playList));
        when(playListRepository.save(any(PlayList.class))).thenReturn(playList);

        playListService.updatePlayList(1L, playListDTO);
        assertEquals("My favorite songs", playList.getName());
    }

    @Test
    void update_nonExistingId_throwsNotFound() {
        when(playListRepository.findById(anyLong())).thenReturn(Optional.empty());
        PlayListDTO playListDTO = new PlayListDTO("My songs", Arrays.asList(), 0L);
        assertThrows(ResponseStatusException.class, () -> playListService.updatePlayList(1L, playListDTO));
    }

    @Test
    void delete_existingId_playlistDeleted() {
        PlayList playList = new PlayList();
        playList.setId(1L);
        when(playListRepository.findById(1L)).thenReturn(Optional.of(playList));
        doNothing().when(playListRepository).deleteById(anyLong());

        playListService.deletePlayList(1L);
        verify(playListRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_nonExistingId_throwsNotFound() {
        when(playListRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> playListService.deletePlayList(1L));
    }
}
