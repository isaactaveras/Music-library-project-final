package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.dtos.GenreDTO;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
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
class GenreControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private GenreRepository genreRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Genre genre;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        genre = new Genre("Metal");
        genre = genreRepository.save(genre);
    }

    @AfterEach
    void tearDown() {
        genreRepository.deleteAll();
    }

    @Test
    void findById_existingId_genreReturned() throws Exception {
        MvcResult result = mockMvc.perform(get("/genres/id/{id}", genre.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Metal"));
    }

    @Test
    void findById_nonExistingId_throwsNotFound() throws Exception {
        mockMvc.perform(get("/genres/id/{id}", 0L))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validGenre_genreCreated() throws Exception {
        GenreDTO genreDTO = new GenreDTO("Heavy Metal");
        String body = objectMapper.writeValueAsString(genreDTO);

        MvcResult result = mockMvc.perform(post("/genres")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Heavy Metal"));
    }

    @Test
    void update_existingId_genreUpdated() throws Exception {
        GenreDTO genreDTO = new GenreDTO("Heavy Metal");
        String body = objectMapper.writeValueAsString(genreDTO);

        mockMvc.perform(put("/genres/{id}", genre.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Genre updated = genreRepository.findById(genre.getId()).get();
        assertEquals("Heavy Metal", updated.getName());
    }

    @Test
    void update_nonExistingId_throwsNotFound() throws Exception {
        GenreDTO genreDTO = new GenreDTO("Hard Rock");
        String body = objectMapper.writeValueAsString(genreDTO);

        mockMvc.perform(put("/genres/{id}", 0L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_existingId_genreDeleted() throws Exception {
        mockMvc.perform(delete("/genres/{id}/delete", genre.getId()))
                .andExpect(status().isNoContent());

        assertFalse(genreRepository.findById(genre.getId()).isPresent());
    }
}