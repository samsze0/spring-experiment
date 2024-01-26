package com.example.repository;

import com.example.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserRepository {
    public Flux<User> findAll();
    public Mono<User> findByName(String name);
    public <S extends User> Mono<S> save(S entity);
}
