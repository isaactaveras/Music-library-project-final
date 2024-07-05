package com.ironhack.MusicLibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MusicLibrary.dtos.UserDTO;
import com.ironhack.MusicLibrary.model.User;
import com.ironhack.MusicLibrary.repository.UserRepository;
import com.ironhack.MusicLibrary.service.UserService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerUnitTest {

    // Creates an instance of the class and injects the mocks into it.
    @InjectMocks
    private UserController userController;

    // Creates an instance of the mock
    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        // Initializes mocks annotated with @Mock and injects these mocks into the fields annotated with @InjectMocks.
        MockitoAnnotations.openMocks(this);

        // Sets up the MockMvc object to simulate HTTP requests and responses for the specified controller in isolation
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void findById_existingId_userReturned() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Eddie Vedder");

        when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/id/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Eddie Vedder"));
    }

    @Test
    void findById_nonExistingId_throwsNotFound() throws Exception {
        when(userService.findById(anyLong())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/users/id/{id}", 0L))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validUser_userCreated() throws Exception {
        UserDTO userDTO = new UserDTO("Ville Valo", "ville", "1234");
        User user = new User("Ville Valo", "ville", "1234");

        when(userService.createUser(any(UserDTO.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ville Valo"));
    }

    @Test
    void update_existingId_userUpdated() throws Exception {
        UserDTO userDTO = new UserDTO("Eddie vedder", "eddie", "1234");
        User userToUpdate = new User("Ville Valo", "ville", "1234");
        userToUpdate.setId(1L);

        when(userService.updateUser(eq(1L), any(UserDTO.class))).thenReturn(userToUpdate);

        mockMvc.perform(put("/users/{id}", 1L)
                        .content(objectMapper.writeValueAsString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_existingId_userDeleted() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("James Brown");
        when(userService.deleteUser(anyLong())).thenReturn(user);

        mockMvc.perform(delete("/users/{id}/delete", 1L))
                .andExpect(status().isNoContent());
    }
}
