package com.example.fullstackbookjwtspringboot.updateDataWeb.Service.Impl;

import com.example.fullstackbookjwtspringboot.film.Dto.FilmDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Film;
import com.example.fullstackbookjwtspringboot.film.Repo.FilmRepo;
import com.example.fullstackbookjwtspringboot.updateDataWeb.Service.FilmService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
@Log4j2
public class FilmImpl implements FilmService {
    private final FilmRepo filmRepo;

    public FilmImpl(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    @Override
    public Film addFilm(FilmDTO filmDTO) {
        log.info("save film");
        Film film = new Film(filmDTO.getName(),filmDTO.getLinkimage(),filmDTO.getPrice(),filmDTO.getCategory(),filmDTO.getDesciption());

        return filmRepo.save(film);
    }

    @Override
    public int findIdFilmByName(String name) throws SQLException {
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/loginrole","root","password");
        Statement stmt=con.createStatement();
        String sql="SELECT id FROM filmcinema.film WHERE name = \""+ name+"\";";
        ResultSet rs= stmt.executeQuery(sql);
        int idx=0;
        rs.next();
        idx=rs.getInt(1);
        con.close();
        return idx;
    }

    @Override
    public List<FilmDTO> getAllFilms() {
        return null;
    }
}
