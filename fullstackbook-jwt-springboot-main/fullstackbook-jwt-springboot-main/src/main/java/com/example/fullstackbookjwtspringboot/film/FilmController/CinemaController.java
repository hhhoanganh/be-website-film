package com.example.fullstackbookjwtspringboot.film.FilmController;

import com.example.fullstackbookjwtspringboot.film.Dto.CinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.FilmCinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.SearchCinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Cinema;
import com.example.fullstackbookjwtspringboot.film.Service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cinema")
public class CinemaController {
    private String valueSearch;

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

    @GetMapping("/filmcinema/{id}/{number}")
    public List<FilmCinemaDTO> getListDetailById(@PathVariable("id")Long id, @PathVariable("number")Long number) throws SQLException {
        List<FilmCinemaDTO> filmCinemaDTO=cinemaService.getListDetailByIdCinema(id,number);
        return filmCinemaDTO;
    }
    @GetMapping("/filmcinema/{id}")
    public int getCountListDetailById(@PathVariable("id")Long id){
        int size=cinemaService.getCountListDetailByIdCinema(id);
        return size;
    }

    @PostMapping(value = {"/cinemas/search"})
    public List<CinemaDTO> getAllCinemaSearch(@RequestBody SearchCinemaDTO searchCinemaDTO) {
        System.out.print(searchCinemaDTO.getCinematype());
        if (searchCinemaDTO.getPageNo() == null) searchCinemaDTO.setPageNo(1);
        if (searchCinemaDTO.getValue() != null && searchCinemaDTO.getValue().equals(" ")) searchCinemaDTO.setValue(null);
        valueSearch=searchCinemaDTO.getValue();
        if(searchCinemaDTO.getValue() == null) return cinemaService.getAllCinema(searchCinemaDTO.getPageNo()-1,10);
        return cinemaService.findAllCinema(searchCinemaDTO);

    }

    @PostMapping(value={"/cinemas/countsearch"})
    public int getNumberOfRecord(@RequestBody SearchCinemaDTO searchCinemaDTO) {
        if(searchCinemaDTO.getValue() == null||searchCinemaDTO.getValue().equals(" ") ){
            return (int)cinemaService.getCountCinema();
        }
        return  cinemaService.getNumberOfRecordCinema(searchCinemaDTO);
    }


}
