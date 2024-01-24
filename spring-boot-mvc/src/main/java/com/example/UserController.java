package com.example;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<?> submitForm(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return ResponseEntity.ok("User is valid");
    }

    @GetMapping("/users/{name}")
    public String getMethodName(@PathVariable String name) {
        return String.format("User %s", name);
    }
    
}
