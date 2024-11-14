package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Room;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByRoomNumber(Long roomNumber);
    @Query("SELECT r FROM Room r LEFT JOIN Booking b ON r.roomNumber = b.room.roomNumber " +
            "WHERE (b.bookingId IS NULL OR (b.checkOutDate <= :checkInDate OR b.checkInDate >= :checkOutDate))")
    List<Room> findAvailableRooms(@Param("checkInDate") LocalDate checkInDate, @Param("checkOutDate") LocalDate checkOutDate);

}
