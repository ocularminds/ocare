/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.common;

import com.google.gson.Gson;
import java.util.Base64;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
public final class Oauth2Exchange {

    private final String username;
    private final String password;
    private final String url;
    private final RestTemplate service;
    private final HttpHeaders headers;
    private boolean authenticated;
    private boolean successful;
    private String token;

    public Oauth2Exchange(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
        headers = new HttpHeaders();
        service = new RestTemplate();
    }

    /**
     *
     * OAuth2 Rest template
     *
     * @param clientId
     * @param secret
     * @param type
     */
    public void authenticate(String clientId, String secret, String type) {
        String credentials = String.format("%s:%s", clientId, secret);
        String encoding = Base64.getEncoder().encodeToString(credentials.getBytes());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String req = String.format(
                "username=%s&password=%s&grant_type=%s",
                username, password, type
        );
        headers.add("Authorization", "Basic " + encoding);
        ResponseEntity<String> result = post(url, req, String.class);
        System.out.println("login response -> " + result.getStatusCodeValue() + " " + result.getBody());
        if (result.getStatusCode().value() == HttpStatus.OK.value()) {
            Map<String, String> m = new Gson().fromJson(result.getBody(), Map.class);
            authenticated = true;
            this.token = m.get("access_token");
            headers.add("Authorization", "Bearer " + token);
        } else {
            System.out.println("login response -> " + result.getStatusCodeValue() + " " + result.getBody());
        }
    }

    public ResponseEntity post(String endpoint, Object data, Class clazz) {
        successful = true;
        try {
            return service.exchange(endpoint, HttpMethod.POST, new HttpEntity<>(data, headers), clazz);
        } catch (HttpClientErrorException e) {
            successful = false;
            return translate(e);
        }
    }

    public ResponseEntity get(String endpoint, Object data, Class clazz) {
        successful = true;
        try {
            return service.exchange(endpoint, HttpMethod.GET, new HttpEntity<>(null, headers), clazz);
        } catch (HttpClientErrorException e) {
            successful = false;
            return translate(e);
        }
    }

    public ResponseEntity put(String endpoint, Object data, Class clazz) {
        successful = true;
        try {
            return service.exchange(endpoint, HttpMethod.PUT, new HttpEntity<>(data, headers), clazz);
        } catch (HttpClientErrorException e) {
            successful = false;
            return translate(e);
        }
    }

    public ResponseEntity delete(String endpoint, Object data, Class clazz) {
        successful = true;
        try {
            return service.exchange(endpoint, HttpMethod.DELETE, new HttpEntity<>(null, headers), clazz);
        } catch (HttpClientErrorException e) {
            successful = false;
            return translate(e);
        }
    }

    public boolean isSuccessful() {
        return successful;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getToken() {
        return this.token;
    }

    public HttpHeaders headers() {
        return this.headers;
    }

    public void setContentType(String typ) {
        headers.add("Content-Type", typ);
    }

    public void setContentType(MediaType typ) {
        headers.setContentType(typ);
    }

    private ResponseEntity<String> translate(HttpClientErrorException e) {
        HttpStatus statusCode = e.getStatusCode();
        String body = e.getResponseBodyAsString();
        return ResponseEntity.status(statusCode).body(body);
        //return new ResponseEntity<>(e.getStatusCode()).;
    }
}
