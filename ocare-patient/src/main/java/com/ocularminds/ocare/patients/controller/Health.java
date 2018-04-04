/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
@RestController
@RequestMapping("/api")
public class Health {
    
    @RequestMapping("/ping")
    public String ping() {
        return "ready";
    }
    
    @RequestMapping("/secure/ping")
    public String securePing() {
        return "pong";
    }
}
