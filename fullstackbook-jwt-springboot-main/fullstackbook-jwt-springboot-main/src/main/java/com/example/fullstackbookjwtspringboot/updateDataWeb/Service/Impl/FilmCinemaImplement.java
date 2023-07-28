package com.example.fullstackbookjwtspringboot.updateDataWeb.Service.Impl;

import com.example.fullstackbookjwtspringboot.film.Entity.FilmCinema;
import com.example.fullstackbookjwtspringboot.film.Repo.FilmCinemaRepo;
import com.example.fullstackbookjwtspringboot.updateDataWeb.Service.FilmCinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmCinemaImplement implements FilmCinemaService {
    private final FilmCinemaRepo filmCinemaRepo;

    @Override
    public FilmCinema addFilmCinema(FilmCinema filmCinema) {
        return filmCinemaRepo.save(filmCinema);
    }

}
