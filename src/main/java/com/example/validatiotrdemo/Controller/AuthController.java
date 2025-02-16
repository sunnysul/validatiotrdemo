package com.example.validatiotrdemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.validatiotrdemo.Auth.JwtService;
import com.example.validatiotrdemo.Model.User;
import com.example.validatiotrdemo.Services.UserServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    UserServices userServices2;
    @Autowired
    JwtService jwtService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (userServices2.veryfyUser(user)) {
            // return ResponseEntity.accepted(jwtService.generateToken(user.getEmail()));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(jwtService.generateToken(user.getEmail()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/register")
    // public String register() {

    // return "User Registration successful";
    // }
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        try {
            user = userServices2.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            System.out.println("---------------------------------");
            System.out.println(e.getMessage());
            System.out.println("---------------------------------");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
        }
    }

}
