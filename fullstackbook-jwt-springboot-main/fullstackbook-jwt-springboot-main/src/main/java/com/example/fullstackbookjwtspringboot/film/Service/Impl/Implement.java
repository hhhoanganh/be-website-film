package com.example.fullstackbookjwtspringboot.film.Service.Impl;


import com.example.fullstackbookjwtspringboot.film.Dto.CinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.FilmCinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.FilmDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Cinema;
import com.example.fullstackbookjwtspringboot.film.Entity.Film;
import com.example.fullstackbookjwtspringboot.film.Repo.CinemaRepo;
import com.example.fullstackbookjwtspringboot.film.Repo.FilmRepo;
import com.example.fullstackbookjwtspringboot.film.Service.CinemaService;
import com.example.fullstackbookjwtspringboot.film.Service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Implement implements FilmService, CinemaService {
//    @Autowired
    private final FilmRepo filmRepo;

    private final CinemaRepo cinemaRepo;



    @Override
    public Film addFilm(FilmDTO filmDTO){
        log.info("save film");
        Film film = new Film(filmDTO.getName(),filmDTO.getLinkimage(),filmDTO.getPrice(),filmDTO.getCategory(),filmDTO.getDesciption());

        return filmRepo.save(film);
    }


    @Override
    public Cinema addCinema(CinemaDTO cinemaDTO){
        log.info("save rap");
        Cinema cinema = new Cinema(cinemaDTO.getName(), cinemaDTO.getAddress(), cinemaDTO.getHotline());
        return cinemaRepo.save(cinema);
    }

    @Override
    public List<Cinema> getAllCinema() {
        return cinemaRepo.findAll();
    }

    @Override
    public int findIdCinemaByName(String name) throws SQLException {
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginrole","root","password");
        Statement stmt=con.createStatement();
        String sql="SELECT id FROM filmcinema.cinema WHERE name = \""+ name+"\";";
        //Film film=filmRepo.findByName(filmDTO.getName());
        ResultSet rs= stmt.executeQuery(sql);
        rs.next();
        int idx=rs.getInt(1);
        con.close();
        return idx;
    }

    @Override
    public Cinema getCinemaById(Long idRap) {
        Cinema cinema= cinemaRepo.findById(Math.toIntExact(idRap));
        return cinema;
    }

    @Override
    public int findIdFilmByName(String name) throws SQLException {
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginrole","root","password");
        Statement stmt=con.createStatement();
        String sql="SELECT id FROM filmcinema.film WHERE name = \""+ name+"\";";
        //Film film=filmRepo.findByName(filmDTO.getName());
        ResultSet rs= stmt.executeQuery(sql);
        rs.next();
        int idx=rs.getInt(1);
        con.close();
        return idx;
    }

    @Override
    public List<FilmDTO> getAllFilms() {
        List<Film> film=filmRepo.findAll();
        List<FilmDTO> films=new ArrayList<>();
        for(Film obj:film){
            FilmDTO filmDTO = new FilmDTO(obj.getId(), obj.getName(), obj.getLinkimage(), obj.getPrice(), obj.getCategory(), obj.getDesciption());
            films.add(filmDTO);
        }
        return films;
    }

    @Override
    public Film getFilmById(Long idFilm)  {
        Film film = filmRepo.findById(Math.toIntExact(idFilm)).get();
        return film;
    }

    @Override
    public List<FilmCinemaDTO> getDetailByIdFilm(Long idFilm) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginrole", "root", "password");
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM filmcinema.film_to_cinema WHERE id_film = \"" + idFilm + "\";";
        //Film film=filmRepo.findByName(filmDTO.getName());
        ResultSet rs = stmt.executeQuery(sql);
        List<FilmCinemaDTO> filmCinemaDTO = new ArrayList<>();
        while (rs.next()){
            int idx = rs.getInt(3);
            int idCinema = rs.getInt(2);
             String time = rs.getString(4);
             String filmName = rs.getString(5);
             String cinemaName = rs.getString(6);
             FilmCinemaDTO film = new FilmCinemaDTO(idx, Math.toIntExact(idFilm), idCinema, time, filmName, cinemaName);
             filmCinemaDTO.add(film);
        }
        con.close();
        return filmCinemaDTO;
    }
    @Override
    public FilmCinemaDTO getDetailByIdCinema(Long idCinema) throws SQLException {

        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginrole","root","password");
        Statement stmt=con.createStatement();
        String sql="SELECT * FROM filmcinema.film_to_cinema WHERE id_cinema = \""+ idCinema+"\";";
        //Film film=filmRepo.findByName(filmDTO.getName());
        ResultSet rs= stmt.executeQuery(sql);
        rs.next();
        int idx=rs.getInt(3);
        int idFilm= rs.getInt(1);
        String time = rs.getString(4);
        String filmName= rs.getString(5);
        String cinemaName= rs.getString(6);
        con.close();
        FilmCinemaDTO film = new FilmCinemaDTO(idx, idFilm,Math.toIntExact(idCinema),time,filmName,cinemaName);
        return film;
    }

}
