package com.example.myfirstproject.service;

import com.example.myfirstproject.model.Client;
import com.example.myfirstproject.repository.ClientRepository;
import com.example.myfirstproject.specifications.ClientSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> getClientByFirstname(String firstname) {
        return clientRepository.findAll(ClientSpecifications.hasFirstname(firstname));
    }

    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
