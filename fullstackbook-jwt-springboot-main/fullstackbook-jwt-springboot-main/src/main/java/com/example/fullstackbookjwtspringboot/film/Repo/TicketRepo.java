package com.example.fullstackbookjwtspringboot.film.Repo;

import com.example.fullstackbookjwtspringboot.film.Dto.TicketDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TicketRepo extends JpaRepository<Ticket, Long> {

    @Transactional
    @Modifying
    @Query(value="DELETE FROM Ticket t WHERE t.idUser= :id")
    void deleteAllByUserId(@Param("id")Long id);
    List<TicketDTO> findAllById(Long id);
}
