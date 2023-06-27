package com.example.fullstackbookjwtspringboot.film.Entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    public Cinema(String name, String address, String hotline) {
        this.name = name;
        this.address = address;
        this.hotline = hotline;
    }
}