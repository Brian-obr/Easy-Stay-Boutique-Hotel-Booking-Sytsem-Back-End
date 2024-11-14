package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Room;
import za.ac.cput.service.impl.RoomService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/save")
    public ResponseEntity<Room> saveRoom(@RequestBody Room room) {
        Room savedRoom = roomService.saveRoom(room);
        return ResponseEntity.ok(savedRoom);
    }

    @GetMapping("/findRoom/{roomNumber}")
    public ResponseEntity<Room> findRoomByRoomNumber(@PathVariable Long roomNumber) {
        Room room = roomService.findRoomByRoomNumber(roomNumber);
            return ResponseEntity.ok(room);
    }

    @DeleteMapping("/delete/{roomNumber}")
    public ResponseEntity<Void> deleteRoomByRoomNumber(@PathVariable Long roomNumber) {
        Room room = roomService.findRoomByRoomNumber(roomNumber);
        if (room != null) {
            roomService.deleteRoomByRoomNumber(roomNumber);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        Room updatedRoom = roomService.updateRoom(room);
        return ResponseEntity.ok(updatedRoom);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> findAllRooms() {
        List<Room> rooms = roomService.findAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms(@RequestParam("checkInDate")
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
                                                        @RequestParam("checkOutDate")
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {
        List<Room> rooms = roomService.findAvailableRooms(checkInDate, checkOutDate);
        return ResponseEntity.ok(rooms);

    }
}
