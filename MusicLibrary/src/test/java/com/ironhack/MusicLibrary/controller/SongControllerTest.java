package com.ironhack.MusicLibrary.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SongControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private Album album;
    private Artist artist;
    private Genre genre;
    private Song song;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        artist = new Artist("Pearl Jam");
        artist = artistRepository.save(artist);

        genre = new Genre("Grunge");
        genre = genreRepository.save(genre);

        album = new Album("Ten", 1991, artist, genre);
        album = albumRepository.save(album);

        song = new Song("Black", 346, artist, genre, album);
        song = songRepository.save(song);

    }

    @AfterEach
    void tearDown() {
        songRepository.deleteAll();
        albumRepository.deleteAll();
        genreRepository.deleteAll();
        artistRepository.deleteAll();
    }

    @Test
    void findById_existingId_songReturned() throws Exception {
        MvcResult result = mockMvc.perform(get("/songs/id/{id}", song.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Black"));
    }

    @Test
    void findById_nonExistingId_throwsNotFound() throws Exception {
        mockMvc.perform(get("/songs/id/{id}", 0L))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validSong_songCreated() throws Exception {
        SongDTO songDTO = new SongDTO("Even Flow", 286, artist.getId(), album.getId(), genre.getId());
        String body = objectMapper.writeValueAsString(songDTO);

        MvcResult result = mockMvc.perform(post("/songs")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Even Flow"));
    }

    @Test
    void update_existingId_songUpdated() throws Exception {
        SongDTO songDTO = new SongDTO("Alive", 341, artist.getId(), album.getId(), genre.getId());
        String body = objectMapper.writeValueAsString(songDTO);

        mockMvc.perform(put("/songs/{id}", song.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Song updated = songRepository.findById(song.getId()).get();
        assertEquals("Alive", updated.getTitle());
    }

    @Test
    void update_nonExistingId_throwsNotFound() throws Exception {
        SongDTO songDTO = new SongDTO("Alive", 341, artist.getId(), genre.getId(), album.getId());
        String body = objectMapper.writeValueAsString(songDTO);

        mockMvc.perform(put("/songs/{id}", 0L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_existingId_songDeleted() throws Exception {
        mockMvc.perform(delete("/songs/{id}/delete", song.getId()))
                .andExpect(status().isNoContent());

        assertFalse(songRepository.findById(song.getId()).isPresent());
    }
}