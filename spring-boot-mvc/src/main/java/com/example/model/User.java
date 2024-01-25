package com.example.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    @NotEmpty
    @Size(min=2, max=30)
    private String name;

    @Min(0)
    @Max(200)
    private Integer age;
}