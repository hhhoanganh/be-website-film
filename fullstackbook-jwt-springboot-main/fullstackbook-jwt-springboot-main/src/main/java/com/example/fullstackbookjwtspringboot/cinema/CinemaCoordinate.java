package com.example.fullstackbookjwtspringboot.cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="coordinates")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CinemaCoordinate {
    @Id
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="phone")
    private String phone;
    @Column(name="longtitude")
    private double longtitude;
    @Column(name="latitude")
    private double latitude;

}
