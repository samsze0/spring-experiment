package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ExampleSpringBootApplication {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    System.setProperty("API_KEY", dotenv.get("API_KEY"));
    SpringApplication.run(ExampleSpringBootApplication.class, args);
  }

}