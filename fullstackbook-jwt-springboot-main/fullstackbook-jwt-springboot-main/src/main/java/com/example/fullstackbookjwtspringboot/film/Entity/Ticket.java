package com.example.fullstackbookjwtspringboot.film.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name= "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "id_user")
    private Long idUser;
    @Column(name = "id_film")
    private Long idFilm;
    @Column(name = "id_cinema")
    private Long idRap;
    @Column(name = "codeticket")
    private String codeTicket;
    @Column(name="film_name")
    private String filmname;
    @Column(name= "cinema_name")
    private String cinemaName;
    @Column(name="time")
    private String time;

    public Ticket( Long idUser, Long idFilm, Long idRap, String codeTicket, String filmname, String cinemaName,String time) {
        this.id = id;
        this.idUser = idUser;
        this.idFilm = idFilm;
        this.idRap = idRap;
        this.codeTicket = codeTicket;
        this.filmname = filmname;
        this.cinemaName = cinemaName;
        this.time=time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

