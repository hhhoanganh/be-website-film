package com.example.fullstackbookjwtspringboot.film.Service;

import com.example.fullstackbookjwtspringboot.film.Dto.FilmCinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.FilmDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Film;

import java.sql.SQLException;
import java.util.List;

public interface FilmService {
    Film addFilm(FilmDTO filmDTO);

    int findIdFilmByName(String name) throws SQLException;
    List<FilmDTO> getAllFilms();

    Film getFilmById(Long idFilm) throws SQLException;

    List<FilmCinemaDTO> getDetailByIdFilm(Long id) throws SQLException;
}
