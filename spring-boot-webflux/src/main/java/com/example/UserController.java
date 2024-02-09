package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.example.model.User;
import com.example.service.UserService;
import com.example.validators.UserValidator;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public Mono<ResponseEntity<String>> createUser(@Valid @RequestBody Mono<User> userMono) {
        return userMono
                .flatMap(user -> {
                    return userService.create(user)
                            .thenReturn(ResponseEntity.ok().body("User created successfully"));
                })
                .onErrorResume(e -> {
                    if (e instanceof WebExchangeBindException) {
                        log.warn("Validation errors while creating user: {}", e.getMessage());
                        return Mono
                                .just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing one or more fields"));
                    } else if (e instanceof ConstraintViolationException) {
                        log.warn("Validation errors while creating user: {}", e.getMessage());
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()));
                    }
                    log.error("Error while creating user", e);
                    return Mono.error(e);
                });
    }

    @PostMapping("/users-with-custom-validator")
    public Mono<ResponseEntity<String>> createUserWithCustomValidator(@RequestBody Mono<User> userMono) {
        return userMono
                .flatMap(user -> {
                    // FIX: user fields are not correctly binded to User object
                    log.info("Creating user", user.getAge(), user.getName());

                    BeanPropertyBindingResult errors = new BeanPropertyBindingResult(user, "user");
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user, errors);
                    if (errors.hasErrors()) {
                        log.warn("Validation errors while creating user: {}", errors);
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid fields"));
                    }
                    return userService.create(user)
                            .thenReturn(ResponseEntity.ok().body("User created successfully"));
                });
    }

    @GetMapping("/users/{name}")
    public Mono<User> getUserByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        return userService.findAll();
    }
}
