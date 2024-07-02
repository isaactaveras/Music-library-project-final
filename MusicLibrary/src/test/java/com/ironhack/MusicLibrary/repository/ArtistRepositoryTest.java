package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.Artist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ArtistRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    @BeforeEach
    void setUp() {
        Artist artist = new Artist();
        artist.setName("Black Sabbath");
        artistRepository.save(artist);
    }

    @AfterEach
    void tearDown() {
        artistRepository.deleteAll();
    }

    @Test
    void saveArtist_newArtist_artistSaved() {
        Artist artist = new Artist();
        artist.setName("The Who");
        Artist savedArtist = artistRepository.save(artist);
        assertNotNull(savedArtist);
        assertEquals("The Who", savedArtist.getName());
    }
}