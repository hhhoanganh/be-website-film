package com.example.fullstackbookjwtspringboot.film.Repo;

import com.example.fullstackbookjwtspringboot.film.Entity.Film;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepo extends PagingAndSortingRepository<Film, Integer   > {
//    List<Film> findAllByFilm(double price, Pageable pageable);
    @Query(value="SELECT u.id FROM Film u WHERE u.name = :name")
    int findIdFilmByName(@Param("name") String name);



    @Query(value="SELECT count(*) FROM Film u WHERE u.name LIKE %:value% AND u.category LIKE %:category%")
    int getNumberOfRecord(@Param("value")String value,@Param("category")String category);

    @Query(value = "SELECT u FROM Film u WHERE u.name LIKE %:value%")
    List<Film> findByValue(@Param("value")String value);

    @Query(value = "SELECT u FROM Film u " +
            "WHERE u.name LIKE %:name% " +
            "AND u.category LIKE %:category% ")
    List<Film> findByValue(@Param("name")String name, @Param("category")String category, Pageable pageable);

    Film findByName(String name);
}
