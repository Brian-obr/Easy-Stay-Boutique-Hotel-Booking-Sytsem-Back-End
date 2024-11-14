package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Room;
import za.ac.cput.domain.enums.RoomType;
import za.ac.cput.factory.RoomFactory;
import za.ac.cput.service.IRoomService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class RoomServiceTest {
    @Autowired
    private IRoomService roomService;

    private static Room room1;
    private static Room room2;
    private static Room room3;
    private static Room room4;

    @BeforeAll
    static void setUp() {
        System.out.println("----------------------------Setup---------------------------------------------");
        room1 = RoomFactory.buildRoom(101L, 150.0, RoomType.SINGLE);
        room2 = RoomFactory.buildRoom(102L, 200.0, RoomType.DOUBLE);
        room3 = RoomFactory.buildRoom(103L, 250.0, RoomType.SUITE);
        room4 = RoomFactory.buildRoom(104L, 300.0, RoomType.PENTHOUSE);
        System.out.println("----------------------------Setup Complete-------------------------------------");
    }

    @Test
    void a_saveRoom() {
        Room savedRoom1 = roomService.saveRoom(room1);
        assertNotNull(savedRoom1);
        assertEquals(101L, savedRoom1.getRoomNumber());
        System.out.println("----------------------------Room: " + savedRoom1.getRoomNumber() + " created-----------------------------------\n" + savedRoom1);

        Room savedRoom2 = roomService.saveRoom(room2);
        assertNotNull(savedRoom2);
        assertEquals(102L, savedRoom2.getRoomNumber());
        System.out.println("----------------------------Room: " + savedRoom2.getRoomNumber() + " created-----------------------------------\n" + savedRoom2);

        Room savedRoom3 = roomService.saveRoom(room3);
        assertNotNull(savedRoom3);
        assertEquals(103L, savedRoom3.getRoomNumber());
        System.out.println("----------------------------Room: " + savedRoom3.getRoomNumber() + " created-----------------------------------\n" + savedRoom3);

        Room savedRoom4 = roomService.saveRoom(room4);
        assertNotNull(savedRoom4);
        assertEquals(104L, savedRoom4.getRoomNumber());
        System.out.println("----------------------------Room: " + savedRoom4.getRoomNumber() + " created-----------------------------------\n" + savedRoom4);
    }

    @Test
    void b_findRoomByNumber() {
        Room savedRoom1 = roomService.saveRoom(room1);
        Room readRoom = roomService.findRoomByRoomNumber(savedRoom1.getRoomNumber());
        assertNotNull(readRoom);
        assertEquals(room1.getRoomNumber(), readRoom.getRoomNumber());
        System.out.println("---------------------------Found: " + readRoom.getRoomNumber() + "-----------------------------------------------\n" + readRoom);
    }

    @Test
    void c_updateRoom() {
        Room savedRoom1 = roomService.saveRoom(room1);
        Room roomToUpdate = new Room.Builder().copy(savedRoom1).setPricePerNight(175.0).build();
        Room updatedRoom = roomService.updateRoom(roomToUpdate);
        assertNotNull(updatedRoom);
        assertEquals(175.0, updatedRoom.getPricePerNight());
        System.out.println("---------------------------Room: " + updatedRoom.getRoomNumber() + " Updated-----------------------------------------------\n" + updatedRoom);
    }

    @Test
    void d_deleteRoomByNumber() {
        Room savedRoom1 = roomService.saveRoom(room1);
        roomService.deleteRoomByRoomNumber(savedRoom1.getRoomNumber());
        Room deletedRoom = roomService.findRoomByRoomNumber(savedRoom1.getRoomNumber());
        assertNull(deletedRoom);
        System.out.println("---------------------------Deleted-----------------------------------------------\nRoom with Number " + room1.getRoomNumber() + " deleted");
    }

    @Test
    void e_findAllRooms() {
        roomService.saveRoom(room1);
        roomService.saveRoom(room2);
        List<Room> rooms = roomService.findAllRooms();
        assertFalse(rooms.isEmpty());
        System.out.println("---------------------------All Rooms-----------------------------------------------\n" + rooms);
    }

    @Test
    void f_findAvailableRooms() {
        LocalDate checkInDate = LocalDate.of(2024, 9, 10);
        LocalDate checkOutDate = LocalDate.of(2024, 9, 20);
        List<Room> availableRooms = roomService.findAvailableRooms(checkInDate, checkOutDate);
        assertNotNull(availableRooms);
        assertFalse(availableRooms.isEmpty());
        System.out.println("---------------------------Available Rooms-----------------------------------------------\n" + availableRooms);
    }
}
