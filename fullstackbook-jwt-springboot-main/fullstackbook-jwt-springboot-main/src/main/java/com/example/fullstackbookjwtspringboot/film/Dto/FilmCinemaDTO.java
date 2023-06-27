package com.example.fullstackbookjwtspringboot.film.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmCinemaDTO {
    private int id;
    private int idFilm;
    private int idCinema;
    private String time;
    private String nameFilm;
    private String nameCinema;

    public FilmCinemaDTO(int id, int idFilm, int idCinema, String time, String nameFilm, String nameCinema) {
        this.id = id;
        this.idFilm = idFilm;
        this.idCinema = idCinema;
        this.time = time;
        this.nameFilm = nameFilm;
        this.nameCinema = nameCinema;
    }
}
