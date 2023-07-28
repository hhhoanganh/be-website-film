package com.example.fullstackbookjwtspringboot.controller;

import com.example.fullstackbookjwtspringboot.dto.JwtResponse;
import com.example.fullstackbookjwtspringboot.dto.SignInRequest;
import com.example.fullstackbookjwtspringboot.dto.SignUpRequest;
import com.example.fullstackbookjwtspringboot.model.ERole;
import com.example.fullstackbookjwtspringboot.model.Role;
import com.example.fullstackbookjwtspringboot.model.User;
import com.example.fullstackbookjwtspringboot.model.UserProfile;
import com.example.fullstackbookjwtspringboot.repository.RoleRepository;
import com.example.fullstackbookjwtspringboot.repository.UserRepository;
import com.example.fullstackbookjwtspringboot.service.UserDetailsImpl;
import com.example.fullstackbookjwtspringboot.service.UserProfileService;
import com.example.fullstackbookjwtspringboot.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final UserProfileService userProfileService;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository,
                          UserProfileService userProfileService, PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userProfileService = userProfileService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        JwtResponse res = new JwtResponse();
        res.setToken(jwt);
        res.setId(userDetails.getId());
        res.setUsername(userDetails.getUsername());
        res.setRoles(roles);
        return ResponseEntity.ok(res);
    }

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
        Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);

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
    @PostMapping("/user/{id}")
    public UserProfile updateProfile(@PathVariable("id")Long id, @RequestBody UserProfile userProfile){
        return userProfileService.updateUser(id,userProfile);
    }
}
