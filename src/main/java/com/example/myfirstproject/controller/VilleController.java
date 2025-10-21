package com.example.myfirstproject.controller;

import com.example.myfirstproject.model.Client;
import com.example.myfirstproject.model.Ville;
import com.example.myfirstproject.repository.NombreClientsParVille;
import com.example.myfirstproject.service.ClientService;
import com.example.myfirstproject.service.VilleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class VilleController {

    private final ClientService clientService;
    @Value("${server.port}") String port;
    @Value("${app.server}") String server;

    final VilleService villeService;

    public VilleController(VilleService villeService, ClientService clientService) {
        this.villeService = villeService;
        this.clientService = clientService;
    }

    @PostMapping("villes")
    public Ville addVille(@RequestBody Ville ville){
        return villeService.addVille(ville);
    }

    @GetMapping("villes")
    public List<Ville> getAllVille(){
        return villeService.getAllVilles();
    }
    @DeleteMapping("villes/{id}")
    public void deleteVille(@PathVariable UUID id){
        villeService.deleteVille(id);
    }

    @PostMapping("villes/{villeName}/clients")
    public Client addClient(
            @RequestPart("photo") MultipartFile photo,
            @RequestPart("client") String clientString,
            @PathVariable String villeName
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Client client = objectMapper.readValue(clientString, Client.class);

        client.setPhoto("http://"+server+":"+port+"/api/photos/"+client.getId());

        Ville ville = villeService.getVilleByName(villeName);
        client.setVille(ville);
        Client returnedClient=clientService.addClient(client);

        String path="src/main/resources/static/photos/"+client.getId()+".jpg";
        photo.transferTo(Path.of(path));
        return client;
    }

    @GetMapping("villes/nbclient")
    public List<NombreClientsParVille> countClientsByVille(){
        return villeService.countClientsByVille();
    }
}
