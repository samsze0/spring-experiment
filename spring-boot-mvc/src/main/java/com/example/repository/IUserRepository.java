package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.model.User;

public interface IUserRepository {
    public List<User> findAll();
    public Optional<User> findByName(String name);
    public void save(User user);
}
