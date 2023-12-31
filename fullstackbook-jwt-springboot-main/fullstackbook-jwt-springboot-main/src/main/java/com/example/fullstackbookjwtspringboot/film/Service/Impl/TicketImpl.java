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
        Ticket ticket = new Ticket(ticketDTO.getIdUser(),ticketDTO.getIdFilm(), ticketDTO.getIdRap(), ticketCode,ticketDTO.getFilmName(),ticketDTO.getCinemaName(), ticketDTO.getTime());
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
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginrole", "root", "password");
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM loginrole.tickets WHERE id_user = \"" + id + "\";";
        //Film film=filmRepo.findByName(filmDTO.getName());
        ResultSet rs = stmt.executeQuery(sql);
        List<TicketDTO> tickets = new ArrayList<>();
        while (rs.next()){
            Long idx = rs.getLong(1);
            Long idUser = rs.getLong(2);
            Long idFilm = rs.getLong(3);
            Long idCinema = rs.getLong(4);
            String codeticket = rs.getString(5);
            String filmName = rs.getString(6);
            String cinemaName = rs.getString(7);
            String time = rs.getString(8);
            TicketDTO ticketDTO = new TicketDTO(idx,idUser,idFilm,idCinema,codeticket,filmName,cinemaName,time);
            tickets.add(ticketDTO);
        }
        con.close();
        return tickets;
    }

    @Override
    public void deleteTicketByIdUser(Long id) {
        ticketRepo.deleteAllByUserId(id);
    }
}
