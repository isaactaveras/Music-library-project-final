package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.dtos.UserDTO;
import com.ironhack.MusicLibrary.model.User;
import com.ironhack.MusicLibrary.repository.UserRepository;
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
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user = new User("Antonio Ruiz", "antonio", "1234");
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findById_existingId_userReturned() throws Exception {
        MvcResult result = mockMvc.perform(get("/users/id/{id}", user.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Antonio Ruiz"));
    }

    @Test
    void findById_nonExistingId_statusNotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/users/id/{id}", -1))
                .andExpect(status().isNotFound())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    void create_validUser_userCreated() throws Exception {
        UserDTO userDTO = new UserDTO("Eddie Vedder", "eddie", "1234");
        String body = objectMapper.writeValueAsString(userDTO);

        MvcResult result = mockMvc.perform(post("/users")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Eddie Vedder"));
    }

    @Test
    void update_existingId_userUpdated() throws Exception {
        UserDTO userDTO = new UserDTO("Ville Valo", "ville", "1234");
        String body = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(put("/users/{id}", user.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        User updated = userRepository.findById(user.getId()).get();
        assertEquals("Ville Valo", updated.getName());
    }

    @Test
    void update_nonExistingId_throwsNotFound() throws Exception {
        UserDTO userDTO = new UserDTO("Ville Valo", "ville", "1234");
        String body = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(put("/users/{id}", 0L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_existingId_albumDeleted() throws Exception {
        mockMvc.perform(delete("/users/{id}/delete", user.getId()))
                .andExpect(status().isNoContent());

        assertFalse(userRepository.findById(user.getId()).isPresent());
    }

}