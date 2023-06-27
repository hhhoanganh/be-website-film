package com.example.fullstackbookjwtspringboot.film.FilmController;

import com.example.fullstackbookjwtspringboot.film.Dto.FilmCinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.FilmDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Film;
import com.example.fullstackbookjwtspringboot.film.Repo.FilmCinemaRepo;
import com.example.fullstackbookjwtspringboot.film.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private FilmCinemaRepo filmCinemaRepo;

    @Autowired
    private FilmService filmService;

    @PostMapping(path="/save")
    public String saveFilm(@RequestBody FilmDTO filmDTO){
        String id = filmService.addFilm(filmDTO).toString();
        return id;
    }

    @GetMapping("/films")
    public List<FilmDTO> getAllFilm(){
        return filmService.getAllFilms();
    }

    @GetMapping("/films/{id}")
    public Film getFilmById(@PathVariable("id") Long id) throws SQLException {
        return filmService.getFilmById(id);
    }

    @GetMapping("/filmcinema/{id}")
    public List<FilmCinemaDTO> getDetailById(@PathVariable("id")Long id) throws SQLException {
        List<FilmCinemaDTO> filmCinemaDTO=filmService.getDetailByIdFilm(id);
        return filmCinemaDTO;
    }
}
