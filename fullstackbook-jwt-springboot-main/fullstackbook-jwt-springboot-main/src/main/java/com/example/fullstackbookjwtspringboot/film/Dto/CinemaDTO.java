package com.example.fullstackbookjwtspringboot.film.Dto;


public class CinemaDTO {

    private int id;
    private String name;

    private String address;

    private String hotline;

    private String cinemaType;

    public String getCinemaType() {
        return cinemaType;
    }

    public void setCinemaType(String cinemaType) {
        this.cinemaType = cinemaType;
    }

    public CinemaDTO(int id, String name, String address, String hotline, String cinemaType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.hotline = hotline;
        this.cinemaType=cinemaType;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotline() {
        return hotline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public CinemaDTO() {
    }

    @Override
    public String toString() {
        return "CinemaDTO{" +
                "id=" + id +
                ", address=" + address +
                ", hotline='" + hotline + '\'' +
                '}';
    }
}
