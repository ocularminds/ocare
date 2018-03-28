/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.oauth2;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
import com.ocularminds.ocare.App;
import com.ocularminds.ocare.common.Oauth2Exchange;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Oauth2Test {

    @LocalServerPort
    private int port;

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void whenTokenDoesNotContainOrganization() {
        String username = "babatope";
        String password = "password";
        String grantType = "password";
        String clientId = "ocare-client";
        String secret = "ocare-secret";
        String url = createURLWithPort("/oauth/token");
        Oauth2Exchange exchange = new Oauth2Exchange(username, password, url);
        exchange.authenticate(clientId, secret, grantType);
        String tokenValue = exchange.getToken();
        //ResponseEntity<String> response = (ResponseEntity) exchange.get(url, null, String.class);
        // OAuth2Authentication auth = tokenStore.readAuthentication(tokenValue);
        // Map<String, Object> details = (Map<String, Object>) auth.getDetails();

        // assertTrue(details.containsKey("organization"));
    }

    private String obtainAccessToken(String clientId, String username, String password) {
        Oauth2Exchange exchange = build();
        return exchange.getToken();
    }

    @Test
    public void testAuthenticationWithInvalidParameters() {
        String grantType = "password";
        String clientId = "ocare1";
        String secret = "ocare-secret";
        Oauth2Exchange exchange = build("567","567","4356345");
        exchange.authenticate(clientId, secret, grantType);
        assertFalse(exchange.isAuthenticated());
    }

    @Test
    public void testAuthenticationWithValidParameters() {
        Oauth2Exchange exchange = build();
        assertTrue(exchange.isAuthenticated());
    }

    @Test
    public void testAccessWithoutToken() {
        Oauth2Exchange exchange = build("ocare-client","675878567","festival.ng");
        String url = createURLWithPort("/api/ping");
        ResponseEntity<String> response = (ResponseEntity) exchange.get(url, null, String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

    }

    private String createURLWithPort(String uri) {
        return String.format("http://localhost:%d%s", port, uri);
    }

    private void populate() {

    }

    public Oauth2Exchange build(String clientId, String secret, String grantType) {
        String username = "helpdesk";
        String password = "password";
        String url = createURLWithPort("/oauth/token");
        Oauth2Exchange exchange = new Oauth2Exchange(username, password, url);
        exchange.authenticate(clientId, secret, grantType);
        return exchange;
    }

    private Oauth2Exchange build() {
        String grantType = "password";
        String clientId = "ocare-client";
        String secret = "ocare-secret";
        return build(clientId, secret, grantType);
    }
}
