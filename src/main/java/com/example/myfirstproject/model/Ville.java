package com.example.myfirstproject.model;

import com.example.myfirstproject.dto.VilleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor @Data
@Entity
public class Ville {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    String name;
    String country;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "ville")
    List<Client> clients;

    public Client addClient(Client client) {
        client.setVille(this);
        this.clients.add(client);
        return client;
    }

    public VilleDTO toDTO(){
        return new VilleDTO(this.id,this.name,this.country);
    }
}
