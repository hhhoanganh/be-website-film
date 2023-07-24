package com.example.fullstackbookjwtspringboot.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaCoordinatesService {
    @Autowired
    private CinemaCoordinateRepo cinemaCoordinateRepo;
    public List<CinemaCoordinate> getAllCinemaCoordinates(){
        return cinemaCoordinateRepo.findAll();
    }
}
