package com.ironhack.MusicLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username is mandatory")
    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<PlayList> playlists;

    @OneToOne
    @JoinColumn(name = "music_library_id", referencedColumnName = "id")
    private MusicLibrary musicLibrary;

    public User(String username, String password, List<PlayList> playlists) {
        this.username = username;
        this.password = password;
        this.playlists = playlists;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PlayList> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlayList> playlists) {
        this.playlists = playlists;
    }

    public MusicLibrary getMusicLibrary() {
        return musicLibrary;
    }

    public void setMusicLibrary(MusicLibrary musicLibrary) {
        this.musicLibrary = musicLibrary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(playlists, user.playlists) && Objects.equals(musicLibrary, user.musicLibrary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, playlists, musicLibrary);
    }
}
