package com.ocularminds.ocare.patients;

import com.ocularminds.ocare.patients.model.Room;
import com.ocularminds.ocare.patients.model.Ward;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by text_ on 19/03/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomRouteTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetWards() throws Exception {
        Ward ward1 = new Ward("Ward 1");
        Ward ward2 = new Ward("Jonathan Ward");
        Ward ward3 = new Ward("Kuti Ward");
        HttpEntity<Ward> entity1 = new HttpEntity<>(ward1, headers);
        HttpEntity<Ward> entity2 = new HttpEntity<>(ward2, headers);
        HttpEntity<Ward> entity3 = new HttpEntity<>(ward3, headers);

        restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, entity1, String.class
        );
        restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, entity2, String.class
        );
        restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, entity3, String.class
        );
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.GET, null, String.class
        );
        List<Ward> records = (List) asJsonObject(response.getBody(), List.class);
        assertTrue(records.size() > 2);//, false
    }

    @Test
    public void testCreateWardWithValidParameters() throws Exception {
        Ward ward3 = new Ward("Studio Sessions");
        HttpEntity<Ward> entity3 = new HttpEntity<>(ward3, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, entity3, String.class
        );
        int EXPECTED = 201;
        assertEquals(EXPECTED, response.getStatusCode().value());
    }

    @Test
    public void testCreateWardWhenNameIsEmpty() throws Exception {
        Ward ward3 = new Ward();
        HttpEntity<Ward> entity3 = new HttpEntity<>(ward3, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, entity3, String.class
        );

        int EXPECTED = 400;
        assertEquals(EXPECTED, response.getStatusCode().value());

    }

    @Test
    public void testCreateWardWithAlreadyUsedName() throws Exception {
        Ward ward3 = new Ward("Beach Party");
        HttpEntity<Ward> entity3 = new HttpEntity<>(ward3, headers);
        restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, entity3, String.class
        );
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, entity3, String.class
        );
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, response.getStatusCode());

    }

    @Test
    public void testGetWardRooms() throws Exception {

        Ward TEST = new Ward("Meetings");
        Room room1 = new Room("Prayer");
        Room room2 = new Room("Sketch");
        Room room3 = new Room("Rehersal");

        HttpEntity<Ward> ward = new HttpEntity<>(TEST, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, ward, String.class
        );
        TEST = (Ward) asJsonObject(response.getBody(), Ward.class);
        restTemplate.exchange(
                createURLWithPort(String.format("/api/ward/%s/room", TEST.getId())),
                HttpMethod.POST, new HttpEntity<>(room1, headers), String.class
        );
        restTemplate.exchange(
                createURLWithPort(String.format("/api/ward/%s/room", TEST.getId())),
                HttpMethod.POST, new HttpEntity<>(room2, headers), String.class
        );
        restTemplate.exchange(
                createURLWithPort(String.format("/api/ward/%s/room", TEST.getId())),
                HttpMethod.POST, new HttpEntity<>(room3, headers), String.class
        );
        response = restTemplate.exchange(
                createURLWithPort(String.format("/api/ward/%s/room", TEST.getId())),
                HttpMethod.GET, null, String.class
        );
        List<Room> records = (List<Room>) asJsonObject(response.getBody(), List.class);
        assertEquals(3, records.size());
    }

    @Test
    public void testDeleteWard() throws Exception {
        Ward TEST = new Ward("Exco Meetings");
        HttpEntity<Ward> entity3 = new HttpEntity<>(TEST, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.GET, null, String.class
        );
        List<Ward> records1 = (List<Ward>) asJsonObject(response.getBody(), List.class);
        response = restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, entity3, String.class
        );
        TEST = (Ward) asJsonObject(response.getBody(), Ward.class);
        restTemplate.exchange(
                createURLWithPort(String.format("/api/ward/%s", TEST.getId())),
                HttpMethod.DELETE, null, String.class
        );
        response = restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.GET, null, String.class
        );
        List<Ward> records2 = (List<Ward>) asJsonObject(response.getBody(), List.class);
        assertEquals(records1.size(), records2.size());
    }

    @Test
    public void testDeleteWardWhenNotAvailable() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(String.format("/api/ward/%s", Long.toString(Long.MAX_VALUE))),
                HttpMethod.DELETE, null, String.class
        );
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteRoom() throws Exception {

        Ward TEST = new Ward("Scrum Session");
        Room room1 = new Room("Issues Review");

        HttpEntity<Ward> entity3 = new HttpEntity<>(TEST, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, entity3, String.class
        );
        TEST = (Ward) asJsonObject(response.getBody(), Ward.class);
        response = restTemplate.exchange(
                createURLWithPort(String.format("/api/ward/%s/room", TEST.getId())),
                HttpMethod.POST, new HttpEntity<>(room1, headers), String.class
        );
        room1 = (Room) asJsonObject(response.getBody(), Room.class);
        restTemplate.exchange(
                createURLWithPort(String.format("/api/ward/%s/room/%s", TEST.getId(),room1.getId())),
                HttpMethod.DELETE, new HttpEntity<>(room1, headers), String.class
        );
        response = restTemplate.exchange(
                createURLWithPort(String.format("/api/ward/%s/room", TEST.getId())),
                HttpMethod.GET, null, String.class
        );
        List<Room> records2 = (List<Room>) asJsonObject(response.getBody(), List.class);
        assertEquals(0, records2.size());
    }

    @Test
    public void testWardWithInvalidRoomParameters() throws Exception {

        Ward TEST = new Ward("Scrum Session 2");

        HttpEntity<Ward> entity3 = new HttpEntity<>(TEST, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, entity3, String.class
        );
        TEST = (Ward) asJsonObject(response.getBody(), Ward.class);
        response = restTemplate.exchange(
                createURLWithPort(
                        String.format("/api/ward/%s/%s", Long.MAX_VALUE, TEST.getId())
                ),
                HttpMethod.DELETE, null, String.class
        );
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testCreateRoomWithNameGreaterThan16k() throws Exception {

        StringBuilder sb = new StringBuilder();
        final String SPACE = " ";
        for (int x = 0; x < 16200; x++) {
            sb.append(Character.toString((char) ((x % 64) + 64)));
            if (x % 26 == 0) {
                sb.append(SPACE);
            }
        }
        Ward TEST = new Ward("Room Checklist");
        Room room1 = new Room(sb.toString());

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/ward"),
                HttpMethod.POST, new HttpEntity<>(TEST, headers), String.class
        );
        TEST = (Ward) asJsonObject(response.getBody(), Ward.class);
        response = restTemplate.exchange(
                createURLWithPort(String.format("/api/ward/%s/room", TEST.getId())),
                HttpMethod.POST, new HttpEntity<>(room1, headers), String.class
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
}
