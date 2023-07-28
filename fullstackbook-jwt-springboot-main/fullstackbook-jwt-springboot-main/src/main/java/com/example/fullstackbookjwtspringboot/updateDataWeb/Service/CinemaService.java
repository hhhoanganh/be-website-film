package com.example.fullstackbookjwtspringboot.updateDataWeb.Service;


import com.example.fullstackbookjwtspringboot.film.Dto.CinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Cinema;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface CinemaService {
    Cinema addCinema(CinemaDTO cinemaDTO);
    List<Cinema> getAllCinema();
    int findIdCinemaByName(String name) throws SQLException;
}
