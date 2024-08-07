package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findById(long id);
    Boolean existsByUsername(String username);

}
