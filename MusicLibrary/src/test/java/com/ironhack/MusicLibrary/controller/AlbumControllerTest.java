package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.repository.AlbumRepository;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
import com.ironhack.MusicLibrary.repository.GenreRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AlbumControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

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

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        artist = new Artist("Pearl Jam");
        artist = artistRepository.save(artist);

        genre = new Genre("Grunge");
        genre = genreRepository.save(genre);

        album = new Album("Ten", 1991, artist, genre);
        album = albumRepository.save(album);
    }

    @AfterEach
    void tearDown() {
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    void findById_existingId_albumReturned() throws Exception {
        MvcResult result = mockMvc.perform(get("/albums/id/{id}", album.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Ten"));
    }

    @Test
    void findById_nonExistingId_throwsNotFound() throws Exception {
        mockMvc.perform(get("/albums/id/{id}", 0L))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validAlbum_albumCreated() throws Exception {
        AlbumDTO albumDTO = new AlbumDTO("Garage Inc.", 1998, artist.getId(), genre.getId());
        String body = objectMapper.writeValueAsString(albumDTO);

        MvcResult result = mockMvc.perform(post("/albums")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Garage Inc."));
    }

    @Test
    void update_existingId_albumUpdated() throws Exception {
        ArtistDTO artistDTO = new ArtistDTO("Red Hot Chili Peppers");
        String body = objectMapper.writeValueAsString(artistDTO);

        mockMvc.perform(put("/artists/{id}", artist.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Artist updated = artistRepository.findById(artist.getId()).get();
        assertEquals("Red Hot Chili Peppers", updated.getName());
    }

    @Test
    void update_nonExistingId_throwsNotFound() throws Exception {
        AlbumDTO albumDTO = new AlbumDTO("Ten", 1990, artist.getId(), genre.getId());
        String body = objectMapper.writeValueAsString(albumDTO);

        mockMvc.perform(put("/albums/{id}", 0L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_existingId_albumDeleted() throws Exception {
        mockMvc.perform(delete("/albums/{id}/delete", album.getId()))
                .andExpect(status().isNoContent());

        assertFalse(albumRepository.findById(album.getId()).isPresent());
    }

}