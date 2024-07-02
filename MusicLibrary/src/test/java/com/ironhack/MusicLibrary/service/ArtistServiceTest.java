package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtistServiceTest {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtistRepository artistRepository;

    @BeforeEach
    void setUp() {
        Artist artist = new Artist();
        artist.setName("AC/DC");
        artistRepository.save(artist);
    }

    @AfterEach
    void tearDown() {
        artistRepository.deleteAll();
    }

    @Test
    void findById_existingId_artistReturned() {
        Artist artist = artistRepository.findAll().get(0);
        Artist found = artistService.findById(artist.getId());
        assertNotNull(found);
        assertEquals("AC/DC", found.getName());
    }

    @Test
    void findById_nonExistingId_throwsNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            artistService.findById(0L);
        });
    }

    @Test
    void create_validArtist_artistSaved() {
        ArtistDTO artistDTO = new ArtistDTO("Megadeth");
        Artist saved = artistService.createArtist(artistDTO);
        assertNotNull(saved);
        assertEquals("Megadeth", saved.getName());
    }

    @Test
    void update_existingId_artistUpdated() {
        Artist artist = artistRepository.findAll().get(0);
        ArtistDTO artistDTO = new ArtistDTO("Guns N' Roses");
        artistService.updateArtist(artist.getId(), artistDTO);
        Artist updated = artistRepository.findById(artist.getId()).get();
        assertEquals("Guns N' Roses", updated.getName());
    }

    @Test
    void update_nonExistingId_throwsNotFound() {
        ArtistDTO artistDTO = new ArtistDTO("Kiss");
        assertThrows(ResponseStatusException.class, () -> {
            artistService.updateArtist(0L, artistDTO);
        });
    }

    @Test
    void delete_existingId_artistDeleted() {
        Artist artist = artistRepository.findAll().get(0);
        artistService.deleteArtist(artist.getId());
        assertFalse(artistRepository.findById(artist.getId()).isPresent());
    }

    @Test
    void delete_nonExistingId_throwsNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            artistService.deleteArtist(0L);
        });
    }

    @Test
    void findById_existingId_returnsArtist() {
        Artist artist = artistRepository.findAll().get(0);
        Artist found = artistService.findById(artist.getId());
        assertNotNull(found);
        assertEquals("AC/DC", found.getName());
    }

    @Test
    void update_existingId_updatesArtist() {
        Artist artist = artistRepository.findAll().get(0);
        ArtistDTO artistDTO = new ArtistDTO("Led Zeppelin");
        artistService.updateArtist(artist.getId(), artistDTO);
        Artist updated = artistRepository.findById(artist.getId()).get();
        assertEquals("Led Zeppelin", updated.getName());
    }

    @Test
    void delete_existingId_deletesArtist() {
        Artist artist = artistRepository.findAll().get(0);
        artistService.deleteArtist(artist.getId());
        assertFalse(artistRepository.findById(artist.getId()).isPresent());
    }
}