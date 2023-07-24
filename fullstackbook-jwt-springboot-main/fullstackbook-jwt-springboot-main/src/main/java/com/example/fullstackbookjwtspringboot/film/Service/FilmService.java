package com.example.fullstackbookjwtspringboot.film.Service;

import com.example.fullstackbookjwtspringboot.film.Dto.FilmCinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.FilmDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.SearchFilmDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Film;

import java.util.List;

public interface FilmService {
    Film addFilm(FilmDTO filmDTO);

    int findIdFilmByName(String name) ;
    List<FilmDTO> getAllFilms(Integer pageNo, Integer pageSize);

    Film getFilmById(Long idFilm) ;

    List<FilmCinemaDTO> getListDetailByIdFilm(Long id,Long number);

    long getCountFilm();

    List<FilmDTO> findByValue(SearchFilmDTO searchFilmDTO);

    int getNumberOfRecord(SearchFilmDTO searchFilmDTO);
    List<FilmDTO> findByValue(String value);
    int getCountListDetailByIdFilm(Long idFilm);


}
