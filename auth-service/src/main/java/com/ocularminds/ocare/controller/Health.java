/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
@RestController
@RequestMapping("/api")
public class Health {
    
    @RequestMapping(value="/health", method = RequestMethod.GET)
    public String health() {
        return "ready";
    }
    
    @RequestMapping(value="/ping", method = RequestMethod.GET)
    public String ping() {
        return "pong";
    }
}
