package com.api.filestorage.controllers;

import java.util.List;

import javax.validation.Valid;

import com.api.filestorage.dto.UserDTO;
import com.api.filestorage.security.payload.request.LoginRequest;
import com.api.filestorage.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(userService.signIn(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO user) {
        if (userService.findByUsername(user.getUsername()) != null)
            return ResponseEntity.badRequest().body("Tên tài khoản đã tồn tại!");
        if (userService.findByEmail(user.getEmail()) != null)
            return ResponseEntity.badRequest().body("Email đã tồn tại!");

        userService.signUp(user);
        return ResponseEntity.ok().body("Success");
    }

}
