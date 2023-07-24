package com.example.fullstackbookjwtspringboot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class UserProfile {

    @Id
    private Long id;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;
    @Column(name="email")
    private String email;
    @Column(name="address")
    private String address;
    @Column(name="phone")
    private String phone;

    public  UserProfile(Long id){
        this.id=id;
    }

}

