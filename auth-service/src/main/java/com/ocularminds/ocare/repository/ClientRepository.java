/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.repository;

import com.ocularminds.ocare.model.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
public interface ClientRepository extends JpaRepository<Client, String> { 
        
    Optional<Client> findById(String id);
    
    Optional<Client> findByClientId(String clientId);
}
