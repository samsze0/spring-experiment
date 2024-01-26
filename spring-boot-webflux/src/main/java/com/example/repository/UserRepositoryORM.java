package com.example.repository;

import reactor.core.publisher.Mono;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.model.User;

@Repository
public interface UserRepositoryORM extends ReactiveCrudRepository<User, Long>, IUserRepository {
    public Mono<User> findByName(String name);
}
