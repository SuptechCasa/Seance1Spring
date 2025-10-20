package com.example.myfirstproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor @Data
@Entity
public class Ville {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    String name;
    String country;
}
