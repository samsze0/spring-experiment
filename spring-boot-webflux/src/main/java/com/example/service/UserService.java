package com.example.service;

import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.IUserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    public Mono<User> create(User user) {
        return this.findByName(user.getName())
                .flatMap(u -> Mono.<User>error(new Exception("User with name " + user.getName() + " already exists")))
                .switchIfEmpty(Mono.defer(() -> userRepository.save(user)));
    }
}