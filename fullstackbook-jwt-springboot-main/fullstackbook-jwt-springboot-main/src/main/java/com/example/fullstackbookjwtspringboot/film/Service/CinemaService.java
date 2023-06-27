package com.example.fullstackbookjwtspringboot.film.Service;

import com.example.fullstackbookjwtspringboot.film.Dto.CinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.FilmCinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Cinema;

import java.sql.SQLException;
import java.util.List;


public interface CinemaService {
    Cinema addCinema(CinemaDTO cinemaDTO);
    List<Cinema> getAllCinema();
    int findIdCinemaByName(String name) throws SQLException;

    Cinema getCinemaById(Long idRap);

    FilmCinemaDTO getDetailByIdCinema(Long id) throws SQLException;
}
