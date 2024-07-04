package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.repository.AlbumRepository;
import com.ironhack.MusicLibrary.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AlbumControllerUnitTest {

    // Creates an instance of the class and injects the mocks into it.
    @InjectMocks
    private AlbumController albumController;

    // Creates an instance of the mock
    @Mock
    private AlbumService albumService;

    @Mock
    private AlbumRepository albumRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Initializes mocks annotated with @Mock and injects these mocks into the fields annotated with @InjectMocks.
        MockitoAnnotations.openMocks(this);

        // Sets up the MockMvc object to simulate HTTP requests and responses for the specified controller in isolation
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();
    }

    @Test
    void findById_existingId_albumReturned() throws Exception {
        Album album = new Album();
        album.setId(1L);
        album.setTitle("Metallica");

        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));

        mockMvc.perform(get("/albums/id/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Metallica"));
    }

    @Test
    void findById_nonExistingId_throwsNotFound() throws Exception {
        when(albumService.findById(anyLong())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/albums/id/{id}", 0L))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validAlbum_albumCreated() throws Exception {
        AlbumDTO albumDTO = new AlbumDTO("Tattoo You", 1981, 1L, 1L);
        Album album = new Album("Tattoo You", 1981, new Artist("Rolling Stones"), new Genre("Rock"));

        when(albumService.createAlbum(any(AlbumDTO.class))).thenReturn(album);

        mockMvc.perform(post("/albums")
                        .content(objectMapper.writeValueAsString(albumDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Tattoo You"));
    }

    @Test
    void update_existingId_albumUpdated() throws Exception {
        AlbumDTO albumDTO = new AlbumDTO("Iowa", 1001, 1L,1L);

        Album albumToUpdate = new Album("Iowa", 2001, new Artist("Slipknot"), new Genre("Nu Metal"));
        albumToUpdate.setId(1L);

        when(albumService.updateAlbum(eq(1L), any(AlbumDTO.class))).thenReturn(albumToUpdate);

        mockMvc.perform(put("/albums/{id}", 1L)
                        .content(objectMapper.writeValueAsString(albumDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_existingId_albumDeleted() throws Exception {
        Album album = new Album();
        album.setId(1L);
        album.setTitle("Born in the U.S.A.");
        when(albumService.deleteAlbum(anyLong())).thenReturn(album);

        mockMvc.perform(delete("/albums/{id}/delete", 1L))
                .andExpect(status().isNoContent());
    }
}
