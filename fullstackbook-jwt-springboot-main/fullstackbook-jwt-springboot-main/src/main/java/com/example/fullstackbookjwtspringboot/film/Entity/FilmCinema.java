package com.example.fullstackbookjwtspringboot.film.Entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "film_to_cinema")
@Data
@NoArgsConstructor
public class FilmCinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_film")
    private int idFilm;
    @Column(name = "id_cinema")
    private int idCinema;
    @Column(name = "time")
    private String time;
    @Column(name = "namefilm")
    private String nameFilm;
    @Column(name="namecinema")
    private String nameCinema;

    public FilmCinema(int idFilm, int idCinema, String time, String nameFilm, String nameCinema) {
        this.idFilm = idFilm;
        this.idCinema = idCinema;
        this.time = time;
        this.nameFilm = nameFilm;
        this.nameCinema = nameCinema;
    }

}
