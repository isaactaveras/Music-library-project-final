package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
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
class ArtistControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ArtistRepository artistRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Artist artist;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        artist = new Artist("Metallica");
        artist = artistRepository.save(artist);
    }

    @AfterEach
    void tearDown() {
        artistRepository.deleteAll();
    }

    @Test
    void findById_existingId_artistReturned() throws Exception {
        MvcResult result = mockMvc.perform(get("/artists/id/{id}", artist.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Metallica"));
    }

    @Test
    void findById_nonExistingId_throwsNotFound() throws Exception {
        mockMvc.perform(get("/artists/id/{id}", 0L))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validArtist_artistCreated() throws Exception {
        ArtistDTO artistDTO = new ArtistDTO("RHCP");
        String body = objectMapper.writeValueAsString(artistDTO);

        MvcResult result = mockMvc.perform(post("/artists")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("RHCP"));
    }

    @Test
    void update_existingId_artistUpdated() throws Exception {
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
        ArtistDTO artistDTO = new ArtistDTO("The Beatles");
        String body = objectMapper.writeValueAsString(artistDTO);

        mockMvc.perform(put("/artists/{id}", 0L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_existingId_artistDeleted() throws Exception {
        mockMvc.perform(delete("/artists/{id}/delete", artist.getId()))
                .andExpect(status().isNoContent());

        assertFalse(artistRepository.findById(artist.getId()).isPresent());
    }
}