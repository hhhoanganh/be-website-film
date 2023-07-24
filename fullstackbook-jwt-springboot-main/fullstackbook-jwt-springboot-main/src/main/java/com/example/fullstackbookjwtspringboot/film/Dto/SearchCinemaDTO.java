package com.example.fullstackbookjwtspringboot.film.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCinemaDTO {
    private String value;
    private String cinematype;
    private Integer pageNo;
}
