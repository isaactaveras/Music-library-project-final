package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
