package com.example.fullstackbookjwtspringboot.service;

import com.example.fullstackbookjwtspringboot.model.Password;
import com.example.fullstackbookjwtspringboot.model.User;
import com.example.fullstackbookjwtspringboot.model.UserProfile;
import com.example.fullstackbookjwtspringboot.repository.UserProfileRepo;
import com.example.fullstackbookjwtspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepo userProfileRepo;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserProfile updateUser(Long id, UserProfile user) {
        UserProfile user1 = userProfileRepo.findById(id).get();
        user1.setId(user.getId());
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        user1.setAddress(user.getAddress());
        user1.setPhone(user.getPhone());
        userProfileRepo.save(user1);
        return user1;
    }

    public UserProfile getUser(Long id){
        return userProfileRepo.findById(id).get();
    }

    public User changePassword(Long id, Password password){
        User user = userRepository.findById(id).get();
        String enco = passwordEncoder.encode(password.getOldPassword());
        if(user.getPassword().equals(enco)){
            user.setPassword(passwordEncoder.encode(password.getNewPassword()));
            userRepository.save(user);
            return user;
        }
        return null;
    }

    public UserProfile save(UserProfile userProfile) {
        UserProfile userProfile1 = new UserProfile(userProfile.getId());
        userProfile1.setFirstname(" ");
        userProfile1.setLastname(" ");
        userProfile1.setEmail("hanh@gmail.com");
        userProfile1.setAddress(" ");
        userProfile1.setPhone(" ");
        return userProfileRepo.save(userProfile1);
    }

    public void deleteUser(Long id) {
       UserProfile userProfile= userProfileRepo.findById(id).get();
       if (userProfile != null)
       userProfileRepo.delete(userProfile);
    }
}
