package com.example.fullstackbookjwtspringboot.updateDataWeb.Service.Impl;

import com.example.fullstackbookjwtspringboot.film.Dto.CinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Cinema;
import com.example.fullstackbookjwtspringboot.film.Repo.CinemaRepo;
import com.example.fullstackbookjwtspringboot.updateDataWeb.Service.CinemaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
@Log4j2
public class CinemaImpl implements CinemaService {
    private final CinemaRepo cinemaRepo;

    public CinemaImpl(CinemaRepo cinemaRepo) {
        this.cinemaRepo = cinemaRepo;
    }

    @Override
    public Cinema addCinema(CinemaDTO cinemaDTO) {
        log.info("save rap");
        Cinema cinema = new Cinema(cinemaDTO.getName(), cinemaDTO.getAddress(), cinemaDTO.getHotline(), cinemaDTO.getCinemaType());
        return cinemaRepo.save(cinema);
    }

    @Override
    public List<Cinema> getAllCinema() {
        return cinemaRepo.findAll();
    }

    @Override
    public int findIdCinemaByName(String name) throws SQLException {
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/loginrole","root","password");
        Statement stmt=con.createStatement();
        String sql="SELECT id FROM filmcinema.cinema WHERE name = \""+ name+"\";";
        ResultSet rs= stmt.executeQuery(sql);
        rs.next();
        int idx=rs.getInt(1);
        con.close();
        return idx;
    }
}
