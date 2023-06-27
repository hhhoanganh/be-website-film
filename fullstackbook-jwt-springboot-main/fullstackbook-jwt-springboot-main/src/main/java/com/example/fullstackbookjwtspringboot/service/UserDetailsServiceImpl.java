package com.example.fullstackbookjwtspringboot.service;

import com.example.fullstackbookjwtspringboot.model.User;
import com.example.fullstackbookjwtspringboot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));
        return UserDetailsImpl.build(user);
    }

    public List<User> getAllUsers() {
        List<User>  users=userRepository.findAll();
        return users;
    }

    public User getUserById(Long id) {
        User user=userRepository.findById(id).get();
        return user;
    }

    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return true;
    }

    public User updateUser(Long id, User user) {
        User user1 = userRepository.findById(id).get();
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return user1;
    }
}
