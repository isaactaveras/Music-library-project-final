package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.dtos.PlayListDTO;
import com.ironhack.MusicLibrary.model.*;
import com.ironhack.MusicLibrary.repository.PlayListRepository;
import com.ironhack.MusicLibrary.service.AlbumService;
import com.ironhack.MusicLibrary.service.PlayListService;
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

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PlayListControllerUnitTest {

    // Creates an instance of the class and injects the mocks into it.
    @InjectMocks
    private PlayListController playListController;

    // Creates an instance of the mock
    @Mock
    private PlayListService playListService;

    @Mock
    private PlayListRepository playListRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Initializes mocks annotated with @Mock and injects these mocks into the fields annotated with @InjectMocks.
        MockitoAnnotations.openMocks(this);

        // Sets up the MockMvc object to simulate HTTP requests and responses for the specified controller in isolation
        mockMvc = MockMvcBuilders.standaloneSetup(playListController).build();
    }

    @Test
    void findById_existingId_playlistReturned() throws Exception {
        PlayList playList = new PlayList();
        playList.setId(1L);
        playList.setName("My songs");

        when(playListRepository.findById(1L)).thenReturn(Optional.of(playList));

        mockMvc.perform(get("/playlists/id/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("My songs"));
    }

    @Test
    void findById_nonExistingId_throwsNotFound() throws Exception {
        when(playListService.findById(anyLong())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/playlists/id/{id}", 0L))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validPlaylist_playlistCreated() throws Exception {
        Song song1 = new Song("Alive", 300, new Artist("Pearl Jam"), new Genre("Grunge"),
                new Album("Ten", 1991, new Artist("Pearl Jam"), new Genre("Grunge")));
        Song song2 = new Song("Black", 400, new Artist("Pearl Jam"), new Genre("Grunge"),
                new Album("Ten", 1991, new Artist("Pearl Jam"), new Genre("Grunge")));

        PlayListDTO playListDTO = new PlayListDTO("My favorite songs", Arrays.asList(song1, song2), 1L);
        PlayList playList = new PlayList("My favorite songs", Arrays.asList(song1, song2), Arrays.asList(new User("Manuel Perez", "manuel", "1234")));

        when(playListService.createPlayList(any(PlayListDTO.class))).thenReturn(playList);

        mockMvc.perform(post("/playlists")
                        .content(objectMapper.writeValueAsString(playListDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("My favorite songs"));
    }

    @Test
    void update_existingId_playlistUpdated() throws Exception {
        Song song1 = new Song("Alive", 300, new Artist("Pearl Jam"), new Genre("Grunge"),
                new Album("Ten", 1991, new Artist("Pearl Jam"), new Genre("Grunge")));
        Song song2 = new Song("Black", 400, new Artist("Pearl Jam"), new Genre("Grunge"),
                new Album("Ten", 1991, new Artist("Pearl Jam"), new Genre("Grunge")));

        PlayListDTO playListDTO = new PlayListDTO("My favorite songs", Arrays.asList(song1, song2), 1L);

        PlayList playlistToUpdate = new PlayList("My favorite songs", Arrays.asList(song1, song2), Arrays.asList(new User("Manuel Perez", "manuel", "1234")));
        playlistToUpdate.setId(1L);

        when(playListService.updatePlayList(eq(1L), any(PlayListDTO.class))).thenReturn(playlistToUpdate);

        mockMvc.perform(put("/playlists/{id}", 1L)
                        .content(objectMapper.writeValueAsString(playListDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_existingId_playlistDeleted() throws Exception {
        doNothing().when(playListService).deletePlayList(anyLong());

        mockMvc.perform(delete("/playlists/{id}/delete", 1L))
                .andExpect(status().isNoContent());

        verify(playListService).deletePlayList(1L);
    }
}