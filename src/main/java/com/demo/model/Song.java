package com.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter // lombok
@Setter // lombok
@ToString // lombok
@NoArgsConstructor // lombok: crea el constructor vacío sin argumentos
@AllArgsConstructor // lombok: crea el constructor con todos los params
@Builder
@Table(name = "canciones")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private Integer duration;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // yyyy-MM-dd
    private LocalDate releaseDate = LocalDate.now();

    private String artist;

    private Integer popularity;

    public Song(String title, String artist, Integer duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }
}