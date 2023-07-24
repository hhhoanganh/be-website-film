package com.example.fullstackbookjwtspringboot.film.Dto;

public class TicketDTO {

    private Long id;

    private Long idUser;

    private Long idFilm;

    private Long idRap;

    private String codeTicket;

    private String filmName;
    private String cinemaName;
    private String time;

    public TicketDTO(Long id, Long idUser, Long idFilm, Long idRap, String codeTicket, String filmName, String cinemaName, String time) {
        this.id = id;
        this.idUser = idUser;
        this.idFilm = idFilm;
        this.idRap = idRap;
        this.codeTicket = codeTicket;
        this.filmName = filmName;
        this.cinemaName = cinemaName;
        this.time=time;
    }

    public TicketDTO(Long id, Long idUser, Long idFilm, Long idRap, String filmName, String cinemaName) {
        this.id = id;
        this.idUser = idUser;
        this.idFilm = idFilm;
        this.idRap = idRap;
        this.filmName = filmName;
        this.cinemaName = cinemaName;
    }

    public TicketDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Long idFilm) {
        this.idFilm = idFilm;
    }

    public Long getIdRap() {
        return idRap;
    }

    public void setIdRap(Long idRap) {
        this.idRap = idRap;
    }

    public String getCodeTicket() {
        return codeTicket;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmname(String filmname) {
        this.filmName = filmname;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public void setCodeTicket(String codeTicket) {
        this.codeTicket = codeTicket;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idFilm=" + idFilm +
                ", idRap=" + idRap +
                ", codeTicket='" + codeTicket + '\'' +
                ", filmName='" + filmName + '\'' +
                ", cinemaName='" + cinemaName + '\'' +
                '}';
    }
}

