package com.example.myfirstproject.repository;

import com.example.myfirstproject.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VilleRepository extends JpaRepository<Ville, UUID> {
    Ville findByName(String villeName);
    // Compter le nombre de clients par ville
    @Query("SELECT v.name as ville, COUNT(c) as nombreClients FROM Ville v LEFT JOIN v.clients c GROUP BY v.name")
    List<NombreClientsParVille> countClientsByVille();

    


}
