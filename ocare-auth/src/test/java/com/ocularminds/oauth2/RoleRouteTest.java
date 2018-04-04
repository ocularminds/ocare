package com.ocularminds.oauth2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocularminds.ocare.App;
import com.ocularminds.ocare.common.Oauth2Exchange;
import com.ocularminds.ocare.common.Password;
import com.ocularminds.ocare.model.ResourceLink;
import com.ocularminds.ocare.model.Role;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by text_ on 19/03/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleRouteTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void getRoles() throws Exception {
        Oauth2Exchange exchange = getExchange();
        exchange.setContentType(MediaType.APPLICATION_JSON);
        Role todo1 = new Role("Help Desk ".concat(Password.next(4)));
        Role todo2 = new Role("Pharmacy".concat(Password.next(4)));
        Role todo3 = new Role("Accountant ".concat(Password.next(4)));

        exchange.post(createURLWithPort("/api/roles"), todo1, String.class);
        exchange.post(createURLWithPort("/api/roles"), todo2, String.class);
        exchange.post(createURLWithPort("/api/roles"), todo3, String.class);
        ResponseEntity<String> response = exchange.get(
                createURLWithPort("/api/roles"), null, String.class
        );
        List<Role> records = (List) asJsonObject(response.getBody(), List.class);
        assertTrue(records.size() > 2);//, false
    }

    @Test
    public void testCreateRoleWithValidParameters() throws Exception {
        Oauth2Exchange exchange = getExchange();
        exchange.setContentType(MediaType.APPLICATION_JSON);
        Role todo3 = new Role("CS Procedure ".concat(Password.next(4)));
        ResponseEntity<String> response = exchange.post(
                createURLWithPort("/api/roles"),
                todo3, String.class
        );
        int EXPECTED = 201;
        assertEquals(EXPECTED, response.getStatusCode().value());
    }

    @Test
    public void testCreateRoleWhenNameIsEmpty() throws Exception {
        Oauth2Exchange exchange = getExchange();
        exchange.setContentType(MediaType.APPLICATION_JSON);
        Role todo3 = new Role();
        ResponseEntity<String> response = exchange.post(
                createURLWithPort("/api/roles"),
                todo3, String.class
        );

        int EXPECTED = 400;
        assertEquals(EXPECTED, response.getStatusCode().value());

    }

    @Test
    public void testCreateRoleWithAlreadyUsedName() throws Exception {
        Oauth2Exchange exchange = getExchange();
        exchange.setContentType(MediaType.APPLICATION_JSON);
        Role todo3 = new Role("Create Customer ".concat(Password.next(4)));
        exchange.post(
                createURLWithPort("/api/roles"),
                todo3, String.class
        );
        ResponseEntity<String> response = exchange.post(
                createURLWithPort("/api/roles"),
                todo3, String.class
        );
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, response.getStatusCode());

    }

    @Test
    public void getRoleResources() throws Exception {
        Oauth2Exchange exchange = getExchange();
        exchange.setContentType(MediaType.APPLICATION_JSON);
        Role TEST = new Role("Drama Meetings");
        ResourceLink entry1 = new ResourceLink("Manage User ".concat(Password.next(4)));
        ResourceLink entry2 = new ResourceLink("New Account ".concat(Password.next(4)));
        ResourceLink entry3 = new ResourceLink("Delete Message ".concat(Password.next(4)));
        ResponseEntity<String> response = exchange.post(
                createURLWithPort("/api/roles"),
                TEST, String.class
        );
        TEST = (Role) asJsonObject(response.getBody(), Role.class);
        exchange.post(
                createURLWithPort(String.format("/api/roles/%s", TEST.getId())),
                entry1, String.class
        );
        exchange.post(
                createURLWithPort(String.format("/api/roles/%s", TEST.getId())),
                entry2, String.class
        );
        exchange.post(
                createURLWithPort(String.format("/api/roles/%s", TEST.getId())),
                entry3, String.class
        );
        response = exchange.get(
                createURLWithPort(String.format("/api/roles/%s", TEST.getId())),
                null, String.class
        );
        List<ResourceLink> records = (List<ResourceLink>) asJsonObject(response.getBody(), List.class);
        assertEquals(3, records.size());
    }

    @Test
    public void testDeleteRole() throws Exception {
        Oauth2Exchange exchange = getExchange();
        exchange.setContentType(MediaType.APPLICATION_JSON);
        Role TEST = new Role("Exco Meetings ".concat(Password.next(4)));
        ResponseEntity<String> response = exchange.get(
                createURLWithPort("/api/roles"),
                null, String.class
        );
        List<Role> records1 = (List<Role>) asJsonObject(response.getBody(), List.class);
        response = exchange.post(
                createURLWithPort("/api/roles"),
                TEST, String.class
        );
        TEST = (Role) asJsonObject(response.getBody(), Role.class);
        exchange.delete(
                createURLWithPort(String.format("/api/roles/%s", TEST.getId())),
                null, String.class
        );
        response = exchange.get(
                createURLWithPort("/api/roles"),
                null, String.class
        );
        List<Role> records2 = (List<Role>) asJsonObject(response.getBody(), List.class);
        assertEquals(records1.size(), records2.size());
    }

    @Test
    public void testDeleteRoleWhenNotAvailable() throws Exception {
        Oauth2Exchange exchange = getExchange();
        exchange.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = exchange.delete(
                createURLWithPort(String.format("/api/roles/%s", Long.MAX_VALUE)),
                null, String.class
        );
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteResourceLink() throws Exception {
        Oauth2Exchange exchange = getExchange();
        exchange.setContentType(MediaType.APPLICATION_JSON);
        Role TEST = new Role("Scrum Session ".concat(Password.next(4)));
        ResourceLink entry1 = new ResourceLink("Issues Review ".concat(Password.next(4)));
        
        ResponseEntity<String> response = exchange.post(
                createURLWithPort("/api/roles"),
                TEST, String.class
        );
        TEST = (Role) asJsonObject(response.getBody(), Role.class);
        response = exchange.post(
                createURLWithPort(String.format("/api/roles/%s", TEST.getId())),
               entry1, String.class
        );
        entry1 = (ResourceLink) asJsonObject(response.getBody(), ResourceLink.class);
        exchange.delete(
                createURLWithPort(String.format("/api/roles/%s", entry1.getId())),
                entry1, String.class
        );
        response = exchange.get(
                createURLWithPort(String.format("/api/roles/%s", entry1.getId())),
                null, String.class
        );
        List<ResourceLink> records2 = (List<ResourceLink>) asJsonObject(response.getBody(), List.class);
        assertEquals(0, records2.size());
    }

    @Test
    public void testDeleteResourceLinkWithInvalidResourceLinkParameters() throws Exception {

        Role TEST = new Role("Scrum Session 2 ".concat(Password.next(4)));
        Oauth2Exchange exchange = getExchange();
        exchange.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = exchange.post(
                createURLWithPort("/api/roles"),
                TEST, String.class
        );
        TEST = (Role) asJsonObject(response.getBody(), Role.class);
        response = exchange.delete(
                createURLWithPort(
                        String.format("/api/roles/%s/%s", Long.MAX_VALUE, TEST.getId())
                ),
                null, String.class
        );
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testCreateResourceLinkWithDescriptionGreaterThan16k() throws Exception {
        Oauth2Exchange exchange = getExchange();
        exchange.setContentType(MediaType.APPLICATION_JSON);
        StringBuilder sb = new StringBuilder();
        final String SPACE = " ";
        for (int x = 0; x < 16200; x++) {
            sb.append(Character.toString((char) ((x % 64) + 64)));
            if (x % 26 == 0) {
                sb.append(SPACE);
            }
        }
        Role TEST = new Role("Vital Nurse ".concat(Password.next(4)));
        ResourceLink entry1 = new ResourceLink(sb.toString());

        ResponseEntity<String> response = exchange.post(
                createURLWithPort("/api/roles"),
                TEST, String.class
        );
        TEST = (Role) asJsonObject(response.getBody(), Role.class);
        response = exchange.post(
                createURLWithPort(String.format("/api/roles/%s", TEST.getId())),
                entry1, String.class
        );
        assertEquals(400, response.getStatusCodeValue());
    }

    public static Object asJsonObject(final String json, Class clazz) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String createURLWithPort(String uri) {
        return String.format("http://localhost:%s%s", port, uri);
    }

    private Oauth2Exchange getExchange() {
        String username = "helpdesk";
        String password = "password";
        String grantType = "password";
        String clientId = "ocare-client";
        String secret = "ocare-secret";
        String url = createURLWithPort("/oauth/token");
        Oauth2Exchange exchange = new Oauth2Exchange(username, password, url);
        exchange.authenticate(clientId, secret, grantType);
        return exchange;
    }
}
