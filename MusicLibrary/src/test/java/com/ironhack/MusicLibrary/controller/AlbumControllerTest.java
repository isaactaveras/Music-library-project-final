package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.repository.AlbumRepository;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
import com.ironhack.MusicLibrary.service.AlbumService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AlbumControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumService albumService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Album album;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        album = new Album("Ten", 1990);
        album = albumRepository.save(album);
    }

    @AfterEach
    void tearDown() {
     //   albumRepository.deleteAll();
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

//    @Test
//    void create_validAlbum_albumCreated() throws Exception {
//        AlbumDTO albumDTO = new AlbumDTO("…And Justice for All", 1988, 1L, 1L);
//        String body = objectMapper.writeValueAsString(albumDTO);
//
//        Album createdAlbum = new Album("…And Justice for All", 1988);
//        when(albumService.createAlbum(any(AlbumDTO.class))).thenReturn(createdAlbum);
//
//        MvcResult result = mockMvc.perform(post("/albums")
//                        .content(body)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        assertTrue(result.getResponse().getContentAsString().contains("…And Justice for All"));
//    }

//    @Test
//    void update_existingId_albumUpdated() throws Exception {
//        AlbumDTO albumDTO = new AlbumDTO("Californication", 1999);
//        String body = objectMapper.writeValueAsString(albumDTO);
//
//        mockMvc.perform(put("/albums/{id}", album.getId())
//                        .content(body)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//
//        Album updated = albumRepository.findById(album.getId()).get();
//        assertEquals("Californication", updated.getTitle());
//    }

//    @Test
//    void update_nonExistingId_throwsNotFound() throws Exception {
//        AlbumDTO albumDTO = new AlbumDTO("Ten", 1990);
//        String body = objectMapper.writeValueAsString(albumDTO);
//
//        mockMvc.perform(put("/albums/{id}", 0L)
//                        .content(body)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }

    @Test
    void delete_existingId_albumDeleted() throws Exception {
        mockMvc.perform(delete("/albums/{id}/delete", album.getId()))
                .andExpect(status().isNoContent());

        assertFalse(albumRepository.findById(album.getId()).isPresent());
    }

}