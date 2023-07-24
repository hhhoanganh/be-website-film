package com.example.fullstackbookjwtspringboot.repository;

import com.example.fullstackbookjwtspringboot.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {
}
