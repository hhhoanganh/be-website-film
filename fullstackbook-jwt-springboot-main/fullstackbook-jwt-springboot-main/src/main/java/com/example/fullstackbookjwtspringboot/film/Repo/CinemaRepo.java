package com.example.fullstackbookjwtspringboot.film.Repo;


import com.example.fullstackbookjwtspringboot.film.Entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CinemaRepo extends  JpaRepository<Cinema, Integer>{
    Cinema findById(int id);
}
