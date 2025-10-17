package com.example.myfirstproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor @AllArgsConstructor @Data
public class Client {
    private Long id;
    private String firstname;
    private String lastname;
    private int age;
    private String photo;

}
