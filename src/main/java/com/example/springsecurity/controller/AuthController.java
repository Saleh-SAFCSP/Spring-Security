package com.example.springsecurity.controller;

import com.example.springsecurity.model.User;
import com.example.springsecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body( authService.getUsers());
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody User user){
        String hashedPassword= new BCryptPasswordEncoder().encode(user.getPassword());
        System.out.println(hashedPassword);
        user.setPassword(hashedPassword);
        authService.register(user);
        HashMap hashMap=new HashMap<>();
        hashMap.put("message","User Registered");
        return ResponseEntity.status(200).body(hashMap);
    }

}
