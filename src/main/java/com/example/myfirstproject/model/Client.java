package com.example.myfirstproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class Client {
    private Long id;
    private String firstname;
    private String lastname;
    private int age;
    private String photo;

}
