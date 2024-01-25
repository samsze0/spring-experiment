package com.example;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> submitForm(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        userService.save(user);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        Optional<User> user = userService.findByName(name);
        return user.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}
