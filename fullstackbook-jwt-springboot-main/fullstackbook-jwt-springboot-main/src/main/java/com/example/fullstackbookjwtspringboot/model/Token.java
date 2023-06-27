package com.example.fullstackbookjwtspringboot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "token")
@Data
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long expired;

    @Column
    private Long revoked;

    @Column
    private String token;
    @Column
    private Long user_id;

}