package com.ironhack.MusicLibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "albums")
@DiscriminatorValue("ALBUM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Album extends Media {

    @NotNull(message = "Year is mandatory")
    private Integer year;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "album")
    @JsonIgnore
    private List<Song> songs;

    public Album(String title, int year, Artist artist, Genre genre) {
        super(title);
        this.year = year;
        this.artist = artist;
        this.genre = genre;
    }

}