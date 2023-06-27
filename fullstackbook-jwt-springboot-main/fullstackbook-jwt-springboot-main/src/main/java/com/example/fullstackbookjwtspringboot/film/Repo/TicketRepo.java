package com.example.fullstackbookjwtspringboot.film.Repo;

import com.example.fullstackbookjwtspringboot.film.Dto.TicketDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TicketRepo extends JpaRepository<Ticket, Long> {
    List<TicketDTO> findAllById(Long id);
}
