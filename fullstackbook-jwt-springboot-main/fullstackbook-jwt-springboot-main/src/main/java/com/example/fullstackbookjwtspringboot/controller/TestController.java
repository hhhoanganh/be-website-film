package com.example.fullstackbookjwtspringboot.controller;

import com.example.fullstackbookjwtspringboot.dto.SignUpRequest;
import com.example.fullstackbookjwtspringboot.film.Service.Impl.TicketImpl;
import com.example.fullstackbookjwtspringboot.film.Service.TicketService;
import com.example.fullstackbookjwtspringboot.model.ERole;
import com.example.fullstackbookjwtspringboot.model.Role;
import com.example.fullstackbookjwtspringboot.model.User;
import com.example.fullstackbookjwtspringboot.model.UserProfile;
import com.example.fullstackbookjwtspringboot.repository.RoleRepository;
import com.example.fullstackbookjwtspringboot.repository.UserRepository;
import com.example.fullstackbookjwtspringboot.service.UserDetailsImpl;
import com.example.fullstackbookjwtspringboot.service.UserDetailsServiceImpl;
import com.example.fullstackbookjwtspringboot.service.UserProfileService;
import com.example.fullstackbookjwtspringboot.updateDataWeb.UpdateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/test")
@Log4j2
public class TestController {
    private UserDetailsServiceImpl userService;
    private TicketService ticket;
    private UserProfileService userProfileService;
    private final UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UpdateService updateService;

    public TestController(UserDetailsServiceImpl userservice,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository,
                          UserProfileService userProfileService,
                          UserRepository userRepository,
                          TicketImpl ticket,
                          UpdateService updateService){

        this.userService=userservice;
        this.userProfileService= userProfileService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.ticket=ticket;
        this.updateService = updateService;
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
        ticket.deleteTicketByIdUser(id);
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

    @PreAuthorize("hasRole('SUPERADMIN')")
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {
        if (signUpRequest.getUsername().length()<6){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is not validate");
        }
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already taken");
        }
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!Pattern.compile(regexPattern).matcher(signUpRequest.getEmail()).matches())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is not validate");
        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (!Pattern.compile(regexPassword).matcher(signUpRequest.getPassword()).matches())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is not validate");
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Set<Role> roles = new HashSet<>();
        ERole role = null;
        if (signUpRequest.getRole() == null){
            role=ERole.ROLE_USER;
        } else {
            if (signUpRequest.getRole().equals("ROLE_SUPERADMIN"))
                role = ERole.ROLE_SUPERADMIN;
            if (signUpRequest.getRole().equals("ROLE_ADMIN"))
                role = ERole.ROLE_ADMIN;
        }
        Optional<Role> userRole = roleRepository.findByName(role);

        if (userRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("role not found");
        }
        roles.add(userRole.get());
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(hashedPassword);
        user.setRoles(roles);
        userRepository.save(user);
        UserProfile userProfile = new UserProfile(user.getId());
        userProfileService.save(userProfile);
        return ResponseEntity.ok("User registered success");
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public void updateDataWeb() throws InterruptedException {
        updateService.updateData();
    }
}
