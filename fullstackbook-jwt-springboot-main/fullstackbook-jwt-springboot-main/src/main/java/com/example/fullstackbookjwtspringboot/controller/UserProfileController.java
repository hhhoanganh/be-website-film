package com.example.fullstackbookjwtspringboot.controller;

import com.example.fullstackbookjwtspringboot.model.Password;
import com.example.fullstackbookjwtspringboot.model.User;
import com.example.fullstackbookjwtspringboot.model.UserProfile;
import com.example.fullstackbookjwtspringboot.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userprofile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PutMapping("/updateuser/{id}")
    public UserProfile updateProfileUser(@PathVariable("id")Long id, @RequestBody UserProfile userProfile){
        return userProfileService.updateUser(id,userProfile);
    }

    @GetMapping("/getuser/{id}")
    public UserProfile getUserProfile(@PathVariable("id")Long id){
        return userProfileService.getUser(id);
    }

    @PutMapping("/changepassword/{id}")
    public ResponseEntity changePassword(@PathVariable("id")Long id, @RequestBody Password password){
        User user = userProfileService.changePassword(id,password);
        return ResponseEntity.ok(user);
    }
}
