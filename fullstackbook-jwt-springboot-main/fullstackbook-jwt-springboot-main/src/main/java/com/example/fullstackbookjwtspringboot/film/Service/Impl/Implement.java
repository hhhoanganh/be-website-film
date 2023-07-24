package com.example.fullstackbookjwtspringboot.film.Service.Impl;


import com.example.fullstackbookjwtspringboot.film.Dto.*;
import com.example.fullstackbookjwtspringboot.film.Entity.Cinema;
import com.example.fullstackbookjwtspringboot.film.Entity.Film;
import com.example.fullstackbookjwtspringboot.film.Entity.FilmCinema;
import com.example.fullstackbookjwtspringboot.film.Repo.CinemaRepo;
import com.example.fullstackbookjwtspringboot.film.Repo.FilmCinemaRepo;
import com.example.fullstackbookjwtspringboot.film.Repo.FilmRepo;
import com.example.fullstackbookjwtspringboot.film.Service.CinemaService;
import com.example.fullstackbookjwtspringboot.film.Service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Implement implements FilmService, CinemaService {
//    @Autowired
    private final FilmRepo filmRepo;

    private final CinemaRepo cinemaRepo;

    private final FilmCinemaRepo filmCinemaRepo;



    @Override
    public Film addFilm(FilmDTO filmDTO){
        log.info("save film");
        Film film = new Film(filmDTO.getName(),filmDTO.getLinkimage(),filmDTO.getPrice(),filmDTO.getCategory(),filmDTO.getDesciption());

        return filmRepo.save(film);
    }




    @Override
    public int findIdFilmByName(String name)  {
        int idx=filmRepo.findIdFilmByName(name);
        return idx;
    }

    @Override
    public List<FilmDTO> getAllFilms(Integer pageNo, Integer pageSize) {
        Pageable paging  = PageRequest.of(pageNo-1,pageSize);
        Page<Film> pageResult = filmRepo.findAll(paging);
        List<FilmDTO> filmDTOList = new ArrayList<>();
        if(pageResult.hasContent()){

                for(Film obj: pageResult.getContent()){
                    FilmDTO filmDTO=new FilmDTO(obj.getId(), obj.getName(), obj.getLinkimage(), obj.getPrice(), obj.getCategory(), obj.getDesciption());
                    filmDTOList.add(filmDTO);
                }
        }
        return filmDTOList;
    }

    @Override
    public Film getFilmById(Long idFilm)  {
        Film film = filmRepo.findById(Math.toIntExact(idFilm)).get();
        return film;
    }

    @Override
    public List<FilmCinemaDTO> getListDetailByIdFilm(Long idFilm,Long number)  {
        Pageable pageable = PageRequest.of(Math.toIntExact(number)-1,10);
        List<FilmCinema> filmCinemaList=filmCinemaRepo.getListDetailByIdFilm(Math.toIntExact(idFilm),pageable);
        List<FilmCinemaDTO> filmCinemaDTO = new ArrayList<>();
        for(FilmCinema obj: filmCinemaList){
            FilmCinemaDTO filmCinemaDTO1= new FilmCinemaDTO(obj.getId(), obj.getIdFilm(), obj.getIdCinema(), obj.getTime(), obj.getNameFilm(), obj.getNameCinema());
            filmCinemaDTO.add(filmCinemaDTO1);
        }

        return filmCinemaDTO;
    }
    @Override
    public int getCountListDetailByIdFilm(Long idFilm) {
        int size =filmCinemaRepo.getCountListDetailByIdFilm(Math.toIntExact(idFilm));
        return size;
    }

    @Override
    public long getCountFilm() {
        return filmRepo.count();
    }

    @Override
    public List<FilmDTO> findByValue(SearchFilmDTO searchFilmDTO) {
        Pageable pageable= PageRequest.of(searchFilmDTO.getPageNo()-1,10);
        List<Film> filmList=filmRepo.findByValue(searchFilmDTO.getValue(), searchFilmDTO.getCategory(), pageable );
        List<FilmDTO> filmDTOS= new ArrayList<>();
        for(Film obj:filmList){
            FilmDTO filmDTO=new FilmDTO(obj.getId(), obj.getName(), obj.getLinkimage(), obj.getPrice(), obj.getCategory(), obj.getDesciption());
            filmDTOS.add(filmDTO);
        }

        return filmDTOS;
    }

    @Override
    public int getNumberOfRecord(SearchFilmDTO searchFilmDTO) {
        int numberRecord = filmRepo.getNumberOfRecord(searchFilmDTO.getValue(), searchFilmDTO.getCategory());
        return numberRecord;
    }

    @Override
    public List<FilmDTO> findByValue(String value) {

        List<Film> filmList = filmRepo.findByValue(value);
        List<FilmDTO> filmDTOS= new ArrayList<>();
        for(Film obj:filmList){
            FilmDTO filmDTO=new FilmDTO(obj.getId(), obj.getName(), obj.getLinkimage(), obj.getPrice(), obj.getCategory(), obj.getDesciption());
            filmDTOS.add(filmDTO);
        }
        return filmDTOS;
    }

    @Override
    public FilmCinemaDTO getDetailByIdCinema(Long idCinema) {

        FilmCinema filmCinema=filmCinemaRepo.getDetailByIdCinema(Math.toIntExact(idCinema));
        FilmCinemaDTO film = new FilmCinemaDTO(filmCinema.getId(),filmCinema.getIdFilm(),filmCinema.getIdCinema(),filmCinema.getTime(),filmCinema.getNameFilm(),filmCinema.getNameCinema());
        return film;
    }
    /*------------------------------------------------------------------------------------------------------------------*/
    //cinema

    @Override
    public List<CinemaDTO> getAllCinema(int pageNo, int pageSize) {
        Pageable paging  = PageRequest.of(pageNo-1,pageSize);
        Page<Cinema> pageResult = cinemaRepo.findAll(paging);
        List<CinemaDTO> cinemaDTOS = new ArrayList<>();
        if(pageResult.hasContent()){

            for(Cinema obj: pageResult.getContent()){
                CinemaDTO cinemaDTO = new CinemaDTO(obj.getId(), obj.getName(), obj.getAddress(), obj.getHotline(), obj.getCinemaType());
                cinemaDTOS.add(cinemaDTO);
            }
        }
        return cinemaDTOS;
    }

    @Override
    public int getCountCinema() {
        return (int) cinemaRepo.count();
    }

    @Override
    public List<CinemaDTO> findAllCinema(SearchCinemaDTO searchCinemaDTO) {
        Pageable pageable = PageRequest.of(searchCinemaDTO.getPageNo()-1, 10);
        List<Cinema> cinemaList=cinemaRepo.findAllCinema(searchCinemaDTO.getValue(), searchCinemaDTO.getCinematype(), pageable);
        List<CinemaDTO> cinemaDTOS= new ArrayList<>();
        for(Cinema obj:cinemaList){
            CinemaDTO cinemaDTO = new CinemaDTO(obj.getId(), obj.getName(), obj.getAddress(), obj.getHotline(), obj.getCinemaType());
            cinemaDTOS.add(cinemaDTO);
        }

        return cinemaDTOS;
    }

    @Override
    public int getNumberOfRecordCinema(SearchCinemaDTO searchCinemaDTO) {
        int numberRecord = cinemaRepo.getNumberOfRecordCinema(searchCinemaDTO.getValue(),searchCinemaDTO.getCinematype());
        return numberRecord;
    }

    @Override
    public List<FilmCinemaDTO> getListDetailByIdCinema(Long id,Long number) {
        Pageable pageable = PageRequest.of(Math.toIntExact(number)-1, 10);
        List<FilmCinema> filmCinemaList = filmCinemaRepo.getListDetailByIdCinema(Math.toIntExact(id),pageable);
        List<FilmCinemaDTO> filmCinemaDTOList = new ArrayList<>();
        for(FilmCinema obj:filmCinemaList){
            FilmCinemaDTO filmCinemaDTO = new FilmCinemaDTO(obj.getId(), obj.getIdFilm(), obj.getIdCinema(), obj.getTime(), obj.getNameFilm(), obj.getNameCinema());
            filmCinemaDTOList.add(filmCinemaDTO);
        }
        return filmCinemaDTOList;
    }
    @Override
    public int getCountListDetailByIdCinema(Long id) {
        int size=filmCinemaRepo.getCountListDetailByIdCinema(Math.toIntExact(id));
        return size;
    }

    @Override
    public Cinema addCinema(CinemaDTO cinemaDTO){
        log.info("save rap");
        Cinema cinema = new Cinema(cinemaDTO.getName(), cinemaDTO.getAddress(), cinemaDTO.getHotline(), cinemaDTO.getCinemaType());
        return cinemaRepo.save(cinema);
    }

    @Override
    public List<Cinema> getAllCinema() {
        return cinemaRepo.findAll();
    }

    @Override
    public int findIdCinemaByName(String name) {
        int idx=cinemaRepo.findIdCinemaByName(name);
        return idx;
    }

    @Override
    public Cinema getCinemaById(Long idRap) {
        Cinema cinema= cinemaRepo.findById(Math.toIntExact(idRap));
        return cinema;
    }

}
