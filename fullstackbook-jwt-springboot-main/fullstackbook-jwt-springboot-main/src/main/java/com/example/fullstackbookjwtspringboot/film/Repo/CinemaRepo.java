package com.example.fullstackbookjwtspringboot.film.Repo;


import com.example.fullstackbookjwtspringboot.film.Entity.Cinema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface CinemaRepo extends  JpaRepository<Cinema, Integer>{
    Cinema findById(int id);

    @Query(value="SELECT c FROM Cinema c WHERE c.name like %:value% AND  c.cinemaType like %:type%")
    List<Cinema> findAllCinema(@Param("value")String value, @Param("type")String type, Pageable pageable);

    @Query(value = "SELECT count(*) FROM Cinema c WHERE c.name like %:value% AND  c.cinemaType like %:type%")
    int getNumberOfRecordCinema(@Param("value")String value, @Param("type")String type);

    @Query(value = "SELECT c.id FROM Cinema c WHERE c.name = :name")
    int findIdCinemaByName(@Param("name") String name);

}
