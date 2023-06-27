package com.example.fullstackbookjwtspringboot.film.Service;

import com.example.fullstackbookjwtspringboot.film.Dto.TicketDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketService {
    Ticket addTicket(TicketDTO ticketDTO);

    boolean deleteTicket(Long id);

    List<TicketDTO> findByIdUser(Long id) throws SQLException;
}
