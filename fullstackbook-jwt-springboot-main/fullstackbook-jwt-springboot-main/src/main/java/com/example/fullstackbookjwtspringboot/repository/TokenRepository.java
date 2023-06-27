package com.example.fullstackbookjwtspringboot.repository;

import com.example.fullstackbookjwtspringboot.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
