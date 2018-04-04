/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
public class AuthenticationResponse implements Serializable {

    private String name;

    private List<String> roles;

    private String token;

    public AuthenticationResponse() {
        this(null, new ArrayList<>(), null);
    }

    public AuthenticationResponse(String name) {
        this(name, new ArrayList<>(), null);
    }

    public AuthenticationResponse(String name, List roles, String token) {
        this.name = name;
        this.roles = roles;
        this.token = token;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
}
