package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Room;
import za.ac.cput.repository.RoomRepository;
import za.ac.cput.service.IRoomService;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> findRoomsByRoomNumber(Long roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    @Override
    @Transactional
    public void deleteRoomByRoomNumber(Long roomNumber) {
        roomRepository.deleteById(roomNumber);
    }

    @Override
    @Transactional
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    @Transactional
    public Room findRoomByRoomNumber(Long roomNumber) {
        return roomRepository.findById(roomNumber).orElse(null);
    }

    @Override
    @Transactional
    public List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        return roomRepository.findAvailableRooms(checkInDate, checkOutDate);
    }
}
