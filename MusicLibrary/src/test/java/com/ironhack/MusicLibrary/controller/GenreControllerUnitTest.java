package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.GenreDTO;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class GenreControllerUnitTest {

    // Creates an instance of the class and injects the mocks into it.
    @InjectMocks
    private GenreController genreController;

    // Creates an instance of the mock
    @Mock
    private GenreService genreService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Initializes mocks annotated with @Mock and injects these mocks into the fields annotated with @InjectMocks.
        MockitoAnnotations.openMocks(this);

        // Sets up the MockMvc object to simulate HTTP requests and responses for the specified controller in isolation
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
    }

//    @Test
//    void findById_existingId_genreReturned() throws Exception {
//        Genre genre = new Genre();
//        genre.setId(1L);
//        genre.setName("Metal");
//
//        when(genreService.findById(1L)).thenReturn(genre);
//
//        mockMvc.perform(get("/genres/id/{id}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Metal"));
//    }

//    @Test
//    void findById_nonExistingId_throwsNotFound() throws Exception {
//        when(genreService.findById(anyLong())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        mockMvc.perform(get("/genres/id/{id}", 0L))
//                .andExpect(status().isNotFound());
//    }

    @Test
    void create_validGenre_GenreCreated() throws Exception {
        GenreDTO genreDTO = new GenreDTO("Rock and Roll");
        Genre genre = new Genre("Rock and Roll");

        when(genreService.createGenre(any(GenreDTO.class))).thenReturn(genre);

        mockMvc.perform(post("/genres")
                        .content(objectMapper.writeValueAsString(genreDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Rock and Roll"));
    }

//    @Test
//    void update_existingId_genreUpdated() throws Exception {
//        GenreDTO genreDTO = new GenreDTO("Pop");
//        doNothing().when(genreService).updateGenre(anyLong(), any(GenreDTO.class));
//
//        mockMvc.perform(put("/genres/id/{id}", 1L)
//                        .content(objectMapper.writeValueAsString(genreDTO))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }

//    @Test
//    void delete_existingId_genreDeleted() throws Exception {
//        doNothing().when(genreService).deleteGenre(anyLong());
//
//        mockMvc.perform(delete("/genres/{id}/delete", 1L))
//                .andExpect(status().isNoContent());
//    }

    @Test
    void delete_existingId_genreDeleted() throws Exception {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Test Genre");
        when(genreService.deleteGenre(anyLong())).thenReturn(genre);

        mockMvc.perform(delete("/genres/{id}/delete", 1L))
                .andExpect(status().isNoContent());
    }
}
