package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.dtos.SongDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.model.Song;
import com.ironhack.MusicLibrary.repository.SongRepository;
import com.ironhack.MusicLibrary.service.SongService;
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
public class SongControllerUnitTest {

    // Creates an instance of the class and injects the mocks into it.
    @InjectMocks
    private SongController songController;

    // Creates an instance of the mock
    @Mock
    private SongService songService;

    @Mock
    private SongRepository songRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Initializes mocks annotated with @Mock and injects these mocks into the fields annotated with @InjectMocks.
        MockitoAnnotations.openMocks(this);

        // Sets up the MockMvc object to simulate HTTP requests and responses for the specified controller in isolation
        mockMvc = MockMvcBuilders.standaloneSetup(songController).build();
    }

    @Test
    void findById_existingId_songReturned() throws Exception {
        Song song = new Song();
        song.setId(1L);
        song.setTitle("One");

        when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        mockMvc.perform(get("/songs/id/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("One"));
    }

    @Test
    void findById_nonExistingId_throwsNotFound() throws Exception {
        when(songService.findById(anyLong())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/songs/id/{id}", 0L))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validSong_songCreated() throws Exception {
        SongDTO songDTO = new SongDTO("Even Flow", 286, 1L, 1L, 1L);
        Song song = new Song("Even Flow", 286, new Artist("Pearl Jam"), new Genre("Grunge"),
                new Album("Ten", 1991, new Artist("Pearl Jam"), new Genre("Grunge")));

        when(songService.createSong(any(SongDTO.class))).thenReturn(song);

        mockMvc.perform(post("/songs")
                        .content(objectMapper.writeValueAsString(songDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Even Flow"));
    }

    @Test
    void update_existingId_songUpdated() throws Exception {
        SongDTO songDTO = new SongDTO("Even Flow", 286, 1L, 1L, 1L);
        Song songToUpdate = new Song("Even Flow", 286, new Artist("Pearl Jam"), new Genre("Grunge"),
                new Album("Ten", 1991, new Artist("Pearl Jam"), new Genre("Grunge")));
        songToUpdate.setId(1L);

        when(songService.updateSong(eq(1L), any(SongDTO.class))).thenReturn(songToUpdate);

        mockMvc.perform(put("/songs/{id}", 1L)
                        .content(objectMapper.writeValueAsString(songDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_existingId_songDeleted() throws Exception {
        Song song = new Song();
        song.setId(1L);
        song.setTitle("Release");
        when(songService.deleteSong(anyLong())).thenReturn(song);

        mockMvc.perform(delete("/songs/{id}/delete", 1L))
                .andExpect(status().isNoContent());
    }

}
