package com.ironhack.MusicLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "songs")
@DiscriminatorValue("SONG")
public class Song extends Media {

    @NotNull(message = "Duration is mandatory")
    private int duration; // Duration in seconds

    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "music_library_id", referencedColumnName = "id")
    private MusicLibrary musicLibrary;

    @ManyToOne
    @JoinColumn(name = "play_list_id", referencedColumnName = "id")
    private PlayList playList;

    public Song() {}

    public Song(String title, int duration, Artist artist, Album album, Genre genre) {
        super(title);
        this.duration = duration;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int length) {
        this.duration = length;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public MusicLibrary getMusicLibrary() {
        return musicLibrary;
    }

    public void setMusicLibrary(MusicLibrary musicLibrary) {
        this.musicLibrary = musicLibrary;
    }

    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Song song = (Song) o;
        return duration == song.duration && Objects.equals(artist, song.artist) && Objects.equals(album, song.album) && Objects.equals(genre, song.genre) && Objects.equals(musicLibrary, song.musicLibrary) && Objects.equals(playList, song.playList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duration, artist, album, genre, musicLibrary, playList);
    }
}
