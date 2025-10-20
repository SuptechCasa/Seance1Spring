package com.example.myfirstproject.repository;

import com.example.myfirstproject.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VilleRepository extends JpaRepository<Ville, UUID> {
    Ville findByName(String villeName);

    
}
