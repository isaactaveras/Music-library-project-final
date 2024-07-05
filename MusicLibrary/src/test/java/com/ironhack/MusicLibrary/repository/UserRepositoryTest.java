package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user1 = new User("Ronnie James Dio", "dio", "1234");
        User user2 = new User("Ozzy Osbourne", "ozzy", "1234");
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    @Transactional
    void findByUsername_existingUsername_userReturned() {
        User found = userRepository.findByUsername("dio");
        assertNotNull(found);
        assertEquals("dio", found.getUsername());
        assertEquals("Ronnie James Dio", found.getName());
    }

    @Test
    @Transactional
    void findByUsername_nonExistingUser_returnsNull() {
        User foundUser = userRepository.findByUsername("Paco");
        assertNull(foundUser);
    }

//    @Test
//    @Transactional
//    void findById_existingUser_returnsUser() {
//        User foundUser = userRepository.findById(1L);
//        assertNotNull(foundUser);
//        assertEquals("dio", foundUser.getUsername());
//    }

    @Test
    @Transactional
    void findById_nonExistingUser_returnsNull() {
        User foundUser = userRepository.findById(999L);
        assertNull(foundUser);
    }

    @Test
    @Transactional
    void existsByUsername_existingUsername_trueReturned() {
        Boolean exists = userRepository.existsByUsername("ozzy");
        assertTrue(exists);
    }

    @Test
    @Transactional
    void existsByUsername_nonExistingUsername_falseReturned() {
        Boolean exists = userRepository.existsByUsername("Jose");
        assertFalse(exists);
    }

}