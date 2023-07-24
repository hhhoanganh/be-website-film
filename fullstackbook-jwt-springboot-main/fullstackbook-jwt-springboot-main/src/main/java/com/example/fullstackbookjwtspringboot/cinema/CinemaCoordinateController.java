package com.example.fullstackbookjwtspringboot.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/coordinates")
@RestController
public class CinemaCoordinateController {

    @Autowired
    private CinemaCoordinatesService cinemaCoordinatesService;

    private List<CinemaCoordinate> cinemaCoordinateList;

    @GetMapping("coordinate")
    public List<CinemaCoordinate> getAllCoordinate(){
        cinemaCoordinateList = cinemaCoordinatesService.getAllCinemaCoordinates();
        return cinemaCoordinateList;
    }

}
