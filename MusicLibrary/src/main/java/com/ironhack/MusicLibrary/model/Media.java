package com.ironhack.MusicLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "media_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public abstract class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    public Media(String title) {
        this.title = title;
    }
}