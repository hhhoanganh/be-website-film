package com.example.fullstackbookjwtspringboot.film.Repo;

import com.example.fullstackbookjwtspringboot.film.Entity.FilmCinema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmCinemaRepo extends JpaRepository<FilmCinema, Long> {

    @Query(value="SELECT count( *) FROM FilmCinema f WHERE f.idFilm =  :idFilm ")
    int getCountListDetailByIdFilm(@Param("idFilm")Integer idFilm);

    @Query(value = "SELECT f FROM FilmCinema f WHERE f.idCinema = :idCinema")
    FilmCinema getDetailByIdCinema(@Param("idCinema")Integer idCinema);

    @Query(value = "SELECT f FROM FilmCinema f WHERE f.idFilm = :id ")
    List<FilmCinema> getListDetailByIdFilm(@Param("id")Integer id, Pageable pageable);

    @Query(value = "SELECT f FROM FilmCinema f WHERE f.idCinema = :id")
    List<FilmCinema> getListDetailByIdCinema(@Param("id")Integer id, Pageable pageable);

    @Query(value = "SELECT count(*) FROM FilmCinema f WHERE f.idCinema = :id ")
    int getCountListDetailByIdCinema(@Param("id") Integer id);
}
