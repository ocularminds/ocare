/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.config;

import java.io.Serializable;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
public class Username implements Serializable {

    private String username;

    public Username() {
    }

    public Username(String name) {
        this.username = name;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public static Username build(String principal) {
        if (principal == null) {
            return null;
        }
        return new Username(principal);
    }
}
