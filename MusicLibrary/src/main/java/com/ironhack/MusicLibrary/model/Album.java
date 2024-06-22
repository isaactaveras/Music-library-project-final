package com.ironhack.MusicLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "albums")
@DiscriminatorValue("ALBUM")
public class Album extends Media {

    @NotNull(message = "Year is mandatory")
    private int year;

    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<Song> songs;

    @ManyToOne
    @JoinColumn(name = "music_library_id", referencedColumnName = "id")
    private MusicLibrary musicLibrary;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    public Album(String title, int year, Artist artist, List<Song> songs, MusicLibrary musicLibrary, Genre genre) {
        super(title);
        this.year = year;
        this.artist = artist;
        this.songs = songs;
        this.musicLibrary = musicLibrary;
        this.genre = genre;
    }

    public Album() {}

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Album album = (Album) o;
        return year == album.year && Objects.equals(artist, album.artist) && Objects.equals(songs, album.songs) && Objects.equals(musicLibrary, album.musicLibrary) && Objects.equals(genre, album.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), year, artist, songs, musicLibrary, genre);
    }
}
