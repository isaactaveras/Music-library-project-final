package com.ironhack.MusicLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @OneToMany(mappedBy = "genre")
    private List<Album> albums;

    @OneToMany(mappedBy = "genre")
    private List<Song> songs;

    @ManyToOne
    @JoinColumn(name = "music_library_id", referencedColumnName = "id")
    private MusicLibrary musicLibrary;

    public Genre() {}

    public Genre(String name, List<Album> albums, List<Song> songs, MusicLibrary musicLibrary) {
        this.name = name;
        this.albums = albums;
        this.songs = songs;
        this.musicLibrary = musicLibrary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Genre genre = (Genre) o;
        return id == genre.id && Objects.equals(name, genre.name) && Objects.equals(albums, genre.albums) && Objects.equals(songs, genre.songs) && Objects.equals(musicLibrary, genre.musicLibrary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, albums, songs, musicLibrary);
    }
}
