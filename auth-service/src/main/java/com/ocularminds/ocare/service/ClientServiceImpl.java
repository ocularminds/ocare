/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.service;

/**
 *
 * @author festus.jejelowo@ocularminds.com
 */
import com.ocularminds.ocare.common.Password;
import com.ocularminds.ocare.error.NotFoundException;
import com.ocularminds.ocare.model.Client;
import com.ocularminds.ocare.model.Oauth2Client;
import com.ocularminds.ocare.repository.ClientRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service(value = "clientService")
public class ClientServiceImpl implements ClientDetailsService, ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = findByClientId(clientId);
        return new Oauth2Client(client);
    }

    @Override
    public Client client(String id) {
        String msg = String.format("Client with id=%s was not found", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(msg));
        return client;
    }

    @Override
    public Client findByClientId(String clientId) {
        String msg = String.format("Client with clientId=%s was not found", clientId);
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new NotFoundException(msg));
        return client;
    }

    @Override
    public List<Client> findAll() {
        List<Client> list = new ArrayList<>();
        clientRepository.findAll().iterator().forEachRemaining(list::add);
        if (list.isEmpty()) {
            Client client = new Client("ocare", Password.next(32), "password");
            clientRepository.save(client);
        }
        return list;
    }

    @Override
    public void delete(String id) {
        clientRepository.delete(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
