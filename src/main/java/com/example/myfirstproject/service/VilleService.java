package com.example.myfirstproject.service;

import com.example.myfirstproject.model.Ville;
import com.example.myfirstproject.repository.VilleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VilleService {
    final VilleRepository villeRepository;
    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    public Ville addVille(Ville ville) {
        return villeRepository.save(ville);
    }

    public List<Ville> getAllVilles() {
        return villeRepository.findAll();
    }

    public void deleteVille(UUID id) {
        villeRepository.deleteById(id);
    }

    public Ville getVilleByName(String villeName) {
        return villeRepository.findByName(villeName);
    }
}
