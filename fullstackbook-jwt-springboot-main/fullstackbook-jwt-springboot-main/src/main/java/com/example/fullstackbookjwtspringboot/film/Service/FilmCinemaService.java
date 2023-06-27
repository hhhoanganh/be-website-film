package com.example.fullstackbookjwtspringboot.film.Service;

import com.example.fullstackbookjwtspringboot.film.Entity.FilmCinema;
import org.springframework.stereotype.Service;

@Service
public interface FilmCinemaService {
    FilmCinema addFilmCinema(FilmCinema filmCinema);
}
