package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello java";
    }

    @PostMapping("/")
    public String helloThere(@RequestParam String name) {
        return String.format("Hello there, %s!", name);
    }

}