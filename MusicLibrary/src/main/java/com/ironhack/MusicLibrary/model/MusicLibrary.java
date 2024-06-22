package com.ironhack.MusicLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "music_libraries")
public class MusicLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "User is mandatory")
    @OneToOne(mappedBy = "musicLibrary")
    private User user;

    @OneToMany(mappedBy = "musicLibrary")
    private List<Album> albums;

    @OneToMany(mappedBy = "musicLibrary")
    private List<Song> songs;

    @OneToMany(mappedBy = "musicLibrary")
    private List<Artist> artists;

    @OneToMany(mappedBy = "musicLibrary")
    private List<Genre> genres;

    public MusicLibrary(User user, List<Album> albums, List<Song> songs, List<Artist> artists, List<Genre> genres) {
        this.user = user;
        this.albums = albums;
        this.songs = songs;
        this.artists = artists;
        this.genres = genres;
    }

    public MusicLibrary() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicLibrary that = (MusicLibrary) o;
        return id == that.id && Objects.equals(user, that.user) && Objects.equals(albums, that.albums) && Objects.equals(songs, that.songs) && Objects.equals(artists, that.artists) && Objects.equals(genres, that.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, albums, songs, artists, genres);
    }
}
