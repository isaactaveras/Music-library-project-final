package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.PlayListDTO;
import com.ironhack.MusicLibrary.dtos.SongDTO;
import com.ironhack.MusicLibrary.model.*;
import com.ironhack.MusicLibrary.repository.*;
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

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PlayListControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PlayListController playListController;

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private Album album;
    private Artist artist;
    private Genre genre;
    private Song song;
    private Song song2;
    private User user;
    private PlayList playList;

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

        song2 = new Song("Even Flow", 286, artist, genre, album);
        song2 = songRepository.save(song2);

        user = new User("Antonio Ruiz", "antonio", "1234");
        user = userRepository.save(user);

        playList = new PlayList("My songs", Arrays.asList(song, song2), Arrays.asList(user));
        playListRepository.save(playList);
    }

    @AfterEach
    void tearDown() {
        playListRepository.deleteAll();
        songRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        genreRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findById_existingId_playlistReturned() throws Exception {
        MvcResult result = mockMvc.perform(get("/playlists/id/{id}", playList.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("My songs"));
    }

    @Test
    void findById_nonExistingId_throwsNotFound() throws Exception {
        mockMvc.perform(get("/playlists/id/{id}", 0L))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validPlaylist_playlistCreated() throws Exception {
        PlayListDTO playListDTO = new PlayListDTO("My favorite songs", Arrays.asList(song, song2), user.getId());
        String body = objectMapper.writeValueAsString(playListDTO);

        MvcResult result = mockMvc.perform(post("/playlists")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("favorite"));
    }

    @Test
    void update_existingId_playlistUpdated() throws Exception {
        PlayListDTO playListDTO = new PlayListDTO("My favorite songs", Arrays.asList(song, song2), user.getId());
        String body = objectMapper.writeValueAsString(playListDTO);

        mockMvc.perform(put("/playlists/{id}", playList.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        PlayList updated = playListRepository.findById(playList.getId()).get();
        assertEquals("My favorite songs", updated.getName());
    }

    @Test
    void update_nonExistingId_throwsNotFound() throws Exception {
        PlayListDTO playListDTO = new PlayListDTO("My favorite songs", Arrays.asList(song, song2), user.getId());
        String body = objectMapper.writeValueAsString(playListDTO);

        mockMvc.perform(put("/playlists/{id}", 0L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_existingId_playlistDeleted() throws Exception {
        mockMvc.perform(delete("/playlists/{id}/delete", playList.getId()))
                .andExpect(status().isNoContent());

        assertFalse(playListRepository.findById(playList.getId()).isPresent());
    }

}