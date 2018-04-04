package com.ocularminds.ocare.service;

import com.ocularminds.ocare.model.Client;

import java.util.List;

public interface ClientService {
    
    public Client client(String id);
    
    public Client findByClientId(String clientId);

    Client save(Client client);

    List<Client> findAll();

    void delete(String id);
}
