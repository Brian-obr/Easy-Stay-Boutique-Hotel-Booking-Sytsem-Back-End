package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Room;
import za.ac.cput.domain.enums.RoomType;
import za.ac.cput.factory.RoomFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class RoomControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static Room room;
    private final String BASE_URL = "/easyStayHotel/room";

    @BeforeAll
    static void setUp() {
        room = RoomFactory.buildRoom(101L, 100,  RoomType.SINGLE);
    }

    @Test
    void a_saveRoom() {
        ResponseEntity<Room> response = restTemplate.postForEntity(BASE_URL, room, Room.class);
        assertNotNull(response.getBody());
       // assertEquals(101, response.getBody().getRoomNumber());
        System.out.println("Save Room"+response.getBody());
    }

//    @Test
//    void b_findRoomsByRoomId() {
//        ResponseEntity<List> response = restTemplate.getForEntity(BASE_URL + "101", List.class);
//        assertNotNull(response.getBody());
//        assertFalse(response.getBody().isEmpty());
//    }

    @Test
    void f_deleteRoomById() {
        restTemplate.delete(BASE_URL + "101");
        ResponseEntity<Optional> response = restTemplate.getForEntity(BASE_URL + "room/101", Optional.class);
        assertTrue(response.getBody().isEmpty());
        System.out.println("Delete Room"+response.getBody());
    }

    @Test
    void c_updateRoom() {
        room = new Room.Builder().copy(room).setPricePerNight(1800).build();
        HttpEntity<Room> request = new HttpEntity<>(room);
        ResponseEntity<Room> response = restTemplate.exchange(BASE_URL, HttpMethod.PUT, request, Room.class);
        assertNotNull(response.getBody());
        assertEquals(1800, response.getBody().getPricePerNight());
        System.out.println("Update Room"+response.getBody());
    }

    @Test
    void d_findAllRooms() {
        ResponseEntity<List> response = restTemplate.getForEntity(BASE_URL + "all", List.class);
        assertNotNull(response.getBody());
//        assertFalse(response.getBody().isEmpty());
        System.out.println("Find All Rooms"+response.getBody());
    }

    @Test
    void e_findRoomById() {
        ResponseEntity<Optional> response = restTemplate.getForEntity(BASE_URL + "room/101", Optional.class);
        assertNotNull(response.getBody());
        System.out.println("Find Room"+response.getBody());
    }

}
