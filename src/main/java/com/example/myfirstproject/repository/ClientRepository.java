package com.example.myfirstproject.repository;

import com.example.myfirstproject.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClientRepository {
    Map<Long, Client> clients;

    public ClientRepository() {
        clients = new HashMap<>();
    }

    public List<Client> getAllClients(){
        return new ArrayList<>(clients.values());
    }
    public void addClient(Client client){
        clients.put(client.getId(), client);
    }
    public Client getClient(long id) {
        return clients.get(id);
    }
    //delete client
    public void deleteClient(long id){
        clients.remove(id);
    }

}
