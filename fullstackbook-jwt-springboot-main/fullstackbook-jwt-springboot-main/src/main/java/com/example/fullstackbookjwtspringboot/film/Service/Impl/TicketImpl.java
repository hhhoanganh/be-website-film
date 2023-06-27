package com.example.fullstackbookjwtspringboot.film.Service.Impl;

import com.example.fullstackbookjwtspringboot.film.Dto.TicketDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Ticket;
import com.example.fullstackbookjwtspringboot.film.Repo.TicketRepo;
import com.example.fullstackbookjwtspringboot.film.Service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketImpl implements TicketService {
    private final TicketRepo ticketRepo;
    @Override
    public Ticket addTicket(TicketDTO ticketDTO) {
        log.info("save ticket");
        String ticketCode=ticketDTO.getIdUser()+""+ticketDTO.getIdFilm()+""+ ticketDTO.getIdRap();
        Ticket ticket = new Ticket(ticketDTO.getIdUser(),ticketDTO.getIdFilm(), ticketDTO.getIdRap(), ticketCode,ticketDTO.getFilmName(),ticketDTO.getCinemaName());
        return ticketRepo.save(ticket);
    }

    @Override
    public boolean deleteTicket(Long id) {
        Ticket ticket=ticketRepo.findById(id).get();
        ticketRepo.delete(ticket);
        return true;
    }

    @Override
    public List<TicketDTO> findByIdUser(Long id) throws SQLException {
        List<TicketDTO> tickets = ticketRepo.findAllById(id);
        return tickets;
    }
}
