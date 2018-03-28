/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.model;

/**
 *
 * @author festus.jejelowo@ocularminds.com
 */
import com.ocularminds.ocare.model.Client;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

public class Oauth2Client implements ClientDetails {

    private final Client client;

    public Oauth2Client(Client user) {
        this.client = user;
    }

    public Client getClient() {
        return client;
    }

    public String getId() {
        return client.getId();
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return new HashSet();
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return client.getSecret();
    }

    @Override
    public boolean isScoped() {
        return client.getScope() != null && !client.getScope().isEmpty();
    }

    @Override
    public Set<String> getScope() {
        Set<String> s = new HashSet();
        s.addAll(Arrays.asList(client.getScope()));
        return s;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> s = new HashSet();
        s.addAll(Arrays.asList(client.getAuthorizedGrantTypes()));
        return s;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return new HashSet<>();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return client.getAccessTokenValidity();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return client.getRefreshTokenValidity();
    }

    @Override
    public boolean isAutoApprove(String string) {
        return true;//client.isAutoApprove();
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap<>();//client.getAdditionalInformation();
    }
}
