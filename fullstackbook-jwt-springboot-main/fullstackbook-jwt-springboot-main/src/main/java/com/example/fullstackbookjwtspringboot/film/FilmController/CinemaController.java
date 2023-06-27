package com.example.fullstackbookjwtspringboot.film.FilmController;

import com.example.fullstackbookjwtspringboot.film.Dto.CinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.FilmCinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Cinema;
import com.example.fullstackbookjwtspringboot.film.Service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin
@RequestMapping("api/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping(path="/save")
    public String saveCinema(@RequestBody CinemaDTO cinemaDTO){
        String id = cinemaService.addCinema(cinemaDTO).toString();
        return id;
    }

    @GetMapping(path= "/cinemas/{id}")
    public Cinema getCinemaById(@PathVariable("id") Long id){
        return cinemaService.getCinemaById(id);
    }

    @GetMapping("/filmcinema/{id}")
    public FilmCinemaDTO getDetailById(@PathVariable("id")Long id) throws SQLException {
        FilmCinemaDTO filmCinemaDTO=cinemaService.getDetailByIdCinema(id);
        return filmCinemaDTO;
    }



}
