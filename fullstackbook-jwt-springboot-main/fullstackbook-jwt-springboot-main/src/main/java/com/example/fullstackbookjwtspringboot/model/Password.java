package com.example.fullstackbookjwtspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Password {
    private String oldPassword;
    private String newPassword;
}
