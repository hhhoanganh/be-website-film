package com.example.fullstackbookjwtspringboot.film.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name= "cinema")
@Data
@NoArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "hotline")
    private String hotline;

    @Column(name="cinematype")
    private String cinemaType;

    public Cinema(String name, String address, String hotline, String cinemaType) {
        this.name = name;
        this.address = address;
        this.hotline = hotline;
        this.cinemaType=cinemaType;
    }
}