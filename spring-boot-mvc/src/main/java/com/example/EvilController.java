package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvilController {

    @GetMapping("/evil")
    public String evil() {
        throw new RuntimeException("Something went wrong");
    }
}