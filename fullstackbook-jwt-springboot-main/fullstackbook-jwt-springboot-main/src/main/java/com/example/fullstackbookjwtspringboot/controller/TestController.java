package com.example.fullstackbookjwtspringboot.controller;

import com.example.fullstackbookjwtspringboot.model.User;
import com.example.fullstackbookjwtspringboot.service.UserDetailsImpl;
import com.example.fullstackbookjwtspringboot.service.UserDetailsServiceImpl;
import com.example.fullstackbookjwtspringboot.service.UserProfileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@Log4j2
public class TestController {
    private UserDetailsServiceImpl userService;
    private UserProfileService userProfileService;

    public TestController(UserDetailsServiceImpl userservice,UserProfileService userProfileService){

        this.userService=userservice;
        this.userProfileService= userProfileService;
    }
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER') or hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public UserDetailsImpl profile() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        log.info("username: {}", userDetails.getUsername());
        return userDetails;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('USER') or hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id){
        User user=null;
        user = userService.getUserById(id);
        return user;
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable("id") Long id){
        boolean deleted = false;
        userProfileService.deleteUser(id);
        deleted = userService.deleteUser(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity updateUser(@PathVariable("id")Long id,@RequestBody User user){
        user = userService.updateUser(id,user);
        return ResponseEntity.ok(user);
    }
}
