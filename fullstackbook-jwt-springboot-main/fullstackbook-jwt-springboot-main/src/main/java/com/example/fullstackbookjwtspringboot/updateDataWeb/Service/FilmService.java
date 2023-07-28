package com.example.fullstackbookjwtspringboot.updateDataWeb.Service;


import com.example.fullstackbookjwtspringboot.film.Dto.FilmDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Film;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface FilmService {
    Film addFilm(FilmDTO filmDTO);

    int findIdFilmByName(String name) throws SQLException;
    List<FilmDTO> getAllFilms();

}
