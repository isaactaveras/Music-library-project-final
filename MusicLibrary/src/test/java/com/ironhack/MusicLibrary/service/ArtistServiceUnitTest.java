package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
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
public class ArtistServiceUnitTest {

    // Creates an instance of the class and injects the mocks into it.
    @InjectMocks
    private ArtistService artistService;

    // Creates an instance of the mock
    @Mock
    private ArtistRepository artistRepository;

    @BeforeEach
    void setUp() {
        // Initializes mocks annotated with @Mock and injects these mocks into the fields annotated with @InjectMocks.
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_existingId_artistReturned() {
        when(artistRepository.findById(1L)).thenReturn(Optional.of(new Artist("Metallica")));

        Artist found = artistService.findById(1L);
        assertNotNull(found);
        assertEquals("Metallica", found.getName());
    }

    @Test
    void findById_nonExistingId_throwsNotFound() {
        when(artistRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> artistService.findById(1L));
    }

    @Test
    void create_validArtist_artistSaved() {
        ArtistDTO artistDTO = new ArtistDTO("AC/DC");
        Artist artist = new Artist("AC/DC");
        when(artistRepository.save(any(Artist.class))).thenReturn(artist);

        Artist saved = artistService.createArtist(artistDTO);
        assertNotNull(saved);
        assertEquals("AC/DC", saved.getName());
    }

    @Test
    void update_existingId_artistUpdated() {
        Artist artist = new Artist("Pearl Jam");
        ArtistDTO artistDTO = new ArtistDTO("Nirvana");

        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        when(artistRepository.save(any(Artist.class))).thenReturn(artist);

        artistService.updateArtist(1L, artistDTO);
        assertEquals("Nirvana", artist.getName());
    }

    @Test
    void update_nonExistingId_throwsNotFound() {
        when(artistRepository.findById(anyLong())).thenReturn(Optional.empty());
        ArtistDTO artistDTO = new ArtistDTO("Megadeth");
        assertThrows(ResponseStatusException.class, () -> artistService.updateArtist(1L, artistDTO));
    }

    @Test
    void delete_existingId_artistDeleted() {
        Artist artist = new Artist();
        artist.setId(1L);
        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        doNothing().when(artistRepository).delete(any(Artist.class));

        artistService.deleteArtist(1L);
        verify(artistRepository, times(1)).delete(artist);
    }
}
