package com.example.fullstackbookjwtspringboot.film.FilmController;

import com.example.fullstackbookjwtspringboot.film.Dto.TicketDTO;
import com.example.fullstackbookjwtspringboot.film.Entity.Cinema;
import com.example.fullstackbookjwtspringboot.film.Entity.Film;
import com.example.fullstackbookjwtspringboot.film.Service.CinemaService;
import com.example.fullstackbookjwtspringboot.film.Service.FilmService;
import com.example.fullstackbookjwtspringboot.film.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private CinemaService cinemaService;

    @PostMapping(path="/save")
    public String saveTicket(@RequestBody TicketDTO ticketDTO){
        System.out.print(ticketDTO.toString());
        String id= ticketService.addTicket(ticketDTO).toString();
        //tao ticketcode
        return id;
    }

    @DeleteMapping(path="/ticket/{id}")
    public boolean deleteTicket(@PathVariable("id") Long id){

        return  ticketService.deleteTicket(id);
    }

    @GetMapping(path="/bookedtickets/{id}")
    public List<TicketDTO> bookedTickets(@PathVariable("id")Long id) throws SQLException {
        List<TicketDTO> tickets= ticketService.findByIdUser(id);
        return tickets;
    }

}
