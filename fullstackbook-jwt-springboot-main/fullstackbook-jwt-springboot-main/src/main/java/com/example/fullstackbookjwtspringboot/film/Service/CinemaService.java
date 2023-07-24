package com.example.fullstackbookjwtspringboot.film.Service;

import com.example.fullstackbookjwtspringboot.film.Dto.CinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.FilmCinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.SearchCinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Cinema;

import java.util.List;


public interface CinemaService {
    Cinema addCinema(CinemaDTO cinemaDTO);
    List<Cinema> getAllCinema();
    int findIdCinemaByName(String name);

    Cinema getCinemaById(Long idRap);

    FilmCinemaDTO getDetailByIdCinema(Long id);

    List<CinemaDTO> getAllCinema(int i, int i1);

//    List<CinemaDTO> findAllCinema(String value, Integer pageNo) throws SQLException;

    int getCountCinema();

//    int getNumberOfRecordCinema(String value) throws SQLException;

    List<CinemaDTO> findAllCinema(SearchCinemaDTO searchCinemaDTO);

    int getNumberOfRecordCinema(SearchCinemaDTO searchCinemaDTO);

    List<FilmCinemaDTO> getListDetailByIdCinema(Long id, Long number);
    int getCountListDetailByIdCinema(Long id);
}
