package za.ac.cput.service;

import za.ac.cput.domain.Room;

import java.time.LocalDate;
import java.util.List;

public interface IRoomService {
    Room saveRoom(Room room);

    List<Room> findRoomsByRoomNumber(Long roomNumber);

    void deleteRoomByRoomNumber(Long roomNumber);

    Room updateRoom(Room room);

    List<Room> findAllRooms();

    Room findRoomByRoomNumber(Long roomNumber);

    List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate);
}
