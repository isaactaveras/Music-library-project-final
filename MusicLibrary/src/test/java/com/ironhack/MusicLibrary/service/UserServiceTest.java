package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.UserDTO;
import com.ironhack.MusicLibrary.model.Role;
import com.ironhack.MusicLibrary.model.User;
import com.ironhack.MusicLibrary.repository.RoleRepository;
import com.ironhack.MusicLibrary.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        Role role = new Role();
        role.setName("ROLE_TEST");
        roleRepository.save(role);

        User user = new User("Tom Morello", "tom", "1234");
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Test
    @Transactional
    void loadUserByUsername_existingUsername_userDetailsReturned() {
        UserDetails userDetails = userService.loadUserByUsername("tom");
        assertNotNull(userDetails);
        assertEquals("tom", userDetails.getUsername());
    }

    @Test
    @Transactional
    void loadUserByUsername_nonExistingUsername_throwsUsernameNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("nonExistingUser");
        });
    }

    @Test
    @Transactional
    void createUser_validUser_userSaved() {
        UserDTO userDTO = new UserDTO("Corey Taylor", "corey", "1234");
        User saved = userService.createUser(userDTO);
        assertNotNull(saved);
        assertEquals("corey", saved.getUsername());
    }

    @Test
    @Transactional
    void createUser_existingUsername_throwsConflict() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("tom");
        userDTO.setPassword("newPassword");
        userDTO.setName("New User");

        assertThrows(ResponseStatusException.class, () -> {
            userService.createUser(userDTO);
        });
    }

    @Test
    @Transactional
    void getUser_existingUsername_userReturned() {
        User user = userService.getUser("tom");
        assertNotNull(user);
        assertEquals("tom", user.getUsername());
    }

    @Test
    @Transactional
    void getUser_nonExistingUsername_userReturned() {
        User user = userService.getUser("nonExistingUser");
        assertNull(user);
    }

    @Test
    @Transactional
    void getUsers_allUsers_usersReturned() {
        List<User> users = userService.getUsers();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }

    @Test
    @Transactional
    void saveRole_validRole_roleSaved() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        Role saved = userService.saveRole(role);
        assertNotNull(saved);
        assertEquals("ROLE_ADMIN", saved.getName());
    }

    @Test
    @Transactional
    void addRoleToUser_validRole_roleAdded() {
        userService.addRoleToUser("tom", "ROLE_TEST");
        User user = userRepository.findByUsername("tom");
        assertTrue(user.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_TEST")));
    }

}