package com.example.fullstackbookjwtspringboot.film.FilmController;

import com.example.fullstackbookjwtspringboot.film.Dto.FilmCinemaDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.FilmDTO;
import com.example.fullstackbookjwtspringboot.film.Dto.SearchFilmDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Film;
import com.example.fullstackbookjwtspringboot.film.Repo.FilmCinemaRepo;
import com.example.fullstackbookjwtspringboot.film.Service.FilmService;
import com.example.fullstackbookjwtspringboot.film.Service.Impl.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/film")
public class FilmController {
    private  Film film;
    @Autowired
    private FilmCinemaRepo filmCinemaRepo;
    private String valueSearch;

    @Autowired
    private FilmService filmService;

    @PostMapping(path="/save")
    public String saveFilm(@RequestBody FilmDTO filmDTO){
        String id = filmService.addFilm(filmDTO).toString();
        return id;
    }

    @PostMapping(value = {"/films/search"})
    public List<FilmDTO> getAllFilmSearch(@RequestBody SearchFilmDTO searchFilmDTO) {
        if (searchFilmDTO.getPageNo()== null) searchFilmDTO.setPageNo(1);
        if (searchFilmDTO.getValue()!=null&&searchFilmDTO.getValue().equals(" ")) searchFilmDTO.setValue(null);
        valueSearch=searchFilmDTO.getValue();
        return filmService.findByValue(searchFilmDTO);

    }

    @PostMapping(value={"/films/countsearch"})
    public int getNumberOfRecord(@RequestBody SearchFilmDTO searchFilmDTO) {
        return  filmService.getNumberOfRecord(searchFilmDTO);
    }

    @PostMapping("/films/id/{id}")
    public Film getFilmById(@PathVariable("id") Long id) {
        film = filmService.getFilmById(id);
        return film;
    }

    @GetMapping("/films/id")
    public Film getFilmById() {
        return film;
    }

    @GetMapping("/filmcinema/{id}/{number}")
    public List<FilmCinemaDTO> getDetailById(@PathVariable("id")Long id, @PathVariable("number")Long number) {
        List<FilmCinemaDTO> filmCinemaDTO=filmService.getListDetailByIdFilm(id,number);
        return filmCinemaDTO;
    }
    @GetMapping("/filmcinema/count/{id}")
    public int getCountDetailById(@PathVariable("id")Long id) {
        int size=filmService.getCountListDetailByIdFilm(id);
        return size;
    }

    @GetMapping("/download")
    public void downloadExcel(HttpServletResponse response) throws IOException{
        // Tạo file excel từ dữ liệu
        if (valueSearch == null) valueSearch="";
        List<FilmDTO> filmDTOList = filmService.findByValue(valueSearch);
        List<FilmDTO> filmList = filmDTOList;
        ExportExcel exporter = new ExportExcel(filmList);
        exporter.exportDataToExcel(response);
    }

    @PostMapping("test/{name}")
    public int isExist(@PathVariable("name")String name)  {

        return  filmService.findIdFilmByName(name);
    }
}
