package com.example.fullstackbookjwtspringboot.film.Repo;

import com.example.fullstackbookjwtspringboot.film.Entity.FilmCinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmCinemaRepo extends JpaRepository<FilmCinema, Long> {
}
