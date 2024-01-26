package com.example.repository;

import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Repository;

import com.example.model.User;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;

import java.util.function.BiFunction;

@Repository
public class UserRepository implements IUserRepository {

  private final DatabaseClient databaseClient;

  private final BiFunction<Row, RowMetadata, User> rowMapper = (row, metadata) -> {
    User user = new User();
    user.setName(row.get("name", String.class));
    user.setAge(row.get("age", Integer.class));
    return user;
  };

  public UserRepository(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

  public Flux<User> findAll() {
    String sql = "SELECT * FROM user";

    return databaseClient.sql(sql).map(rowMapper).all();
  }

  public Mono<User> findByName(String name) {
    String sql = "SELECT * FROM user WHERE name = :name LIMIT 1";

    return databaseClient.sql(sql).bind("name", name).map(rowMapper).first();
  }

  public Mono<User> save(User user) {
    String sql = "INSERT INTO user (name, age) VALUES (:name, :age) ON DUPLICATE KEY UPDATE age = :age";

    return databaseClient.sql(sql)
        .bind("name", user.getName())
        .bind("age", user.getAge())
        .fetch()
        .rowsUpdated()
        .thenReturn(user);
  }
}
