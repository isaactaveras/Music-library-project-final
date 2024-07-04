package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
import com.ironhack.MusicLibrary.service.ArtistService;
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
class ArtistControllerUnitTest {

    // Creates an instance of the class and injects the mocks into it.
    @InjectMocks
    private ArtistController artistController;

    // Creates an instance of the mock
    @Mock
    private ArtistService artistService;

    @Mock
    private ArtistRepository artistRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Initializes mocks annotated with @Mock and injects these mocks into the fields annotated with @InjectMocks.
        MockitoAnnotations.openMocks(this);

        // Sets up the MockMvc object to simulate HTTP requests and responses for the specified controller in isolation
        mockMvc = MockMvcBuilders.standaloneSetup(artistController).build();
    }

    @Test
    void findById_existingId_artistReturned() throws Exception {
        Artist artist = new Artist();
        artist.setId(1L);
        artist.setName("Metallica");

        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));

        mockMvc.perform(get("/artists/id/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Metallica"));
    }

    @Test
    void findById_nonExistingId_throwsNotFound() throws Exception {
        when(artistService.findById(anyLong())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/artists/id/{id}", 0L))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validArtist_artistCreated() throws Exception {
        ArtistDTO artistDTO = new ArtistDTO("Rolling Stones");
        Artist artist = new Artist("Rolling Stones");

        when(artistService.createArtist(any(ArtistDTO.class))).thenReturn(artist);

        mockMvc.perform(post("/artists")
                        .content(objectMapper.writeValueAsString(artistDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Rolling Stones"));
    }

    @Test
    void update_existingId_artistUpdated() throws Exception {
        ArtistDTO artistDTO = new ArtistDTO("Slipknot");

        Artist artistToUpdate = new Artist("Slipknot");
        artistToUpdate.setId(1L);

        when(artistService.updateArtist(eq(1L), any(ArtistDTO.class))).thenReturn(artistToUpdate);

        mockMvc.perform(put("/artists/{id}", 1L)
                        .content(objectMapper.writeValueAsString(artistDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    void delete_existingId_artistDeleted() throws Exception {
        Artist artist = new Artist();
        artist.setId(1L);
        artist.setName("Test Artist");
        when(artistService.deleteArtist(anyLong())).thenReturn(artist);

        mockMvc.perform(delete("/artists/{id}/delete", 1L))
                .andExpect(status().isNoContent());
    }
}
