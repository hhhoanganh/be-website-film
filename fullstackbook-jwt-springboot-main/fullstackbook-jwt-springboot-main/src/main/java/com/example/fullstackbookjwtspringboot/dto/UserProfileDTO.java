package com.example.fullstackbookjwtspringboot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileDTO {

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String address;

    private String phone;

}