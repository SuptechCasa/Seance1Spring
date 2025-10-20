package com.example.myfirstproject.controller;

import com.example.myfirstproject.model.Client;
import com.example.myfirstproject.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("api")
public class ClientController {
    @Value("${server.port}") String port;
    @Value("${app.server}") String server;

    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //Endpoint simple
    @GetMapping("bonjour")
    public String bonjour() {
        return "bonjour";
    }

    //Endpoint avec @RequestParam
    @GetMapping("bonsoir")
    public String bonsoir(@RequestParam(required = false, defaultValue = "NOM") String nom, @RequestParam String age) {
        return "bonsoir " + nom + " votre âge est: " + age;

    }

    //Endpoint avec @PathVariable
    @GetMapping("bonjour/{nom}")
    public String bonjour(@PathVariable String nom) {
        return "bonjour " + nom;
    }

    //Endpoint avec @RequestHeader
    @PostMapping("bonsoir")
    public String bonsoir(@RequestHeader("Authorization") String auth) {
        return "bonsoir " + auth;
    }

    //Endpoint avec @RequestBody
    @GetMapping("client")
    public Client getClient(@RequestBody Client client) {
        client.setAge(40);
        return client;
    }

    //Endpoint avec @RequestParam + fichier
    @PostMapping("client")
    public Client addClient(
            @RequestPart("photo") MultipartFile photo,
            @RequestPart("client") String clientString
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Client client = objectMapper.readValue(clientString, Client.class);

        String path="src/main/resources/static/photos/"+client.getId()+".jpg";
        photo.transferTo(Path.of(path));

        client.setPhoto("http://"+server+":"+port+"/api/photos/"+client.getId());
        clientService.addClient(client);
        return client;
    }

    @GetMapping("photos/{id}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String id) {
        String path="src/main/resources/static/photos/"+id+".jpg";
        FileSystemResource file = new FileSystemResource(path);

        if (!file.exists()){return ResponseEntity.notFound().build();}

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
                .body(file);

    }

    @GetMapping("clients")
    public List<Client> getAllClient() {
        return clientService.getAllClient();
    }

    @DeleteMapping("clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}

