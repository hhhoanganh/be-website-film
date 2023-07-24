package com.example.fullstackbookjwtspringboot.film.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilmDTO {
    private String value;
    private String category;
    private Integer pageNo;
}
