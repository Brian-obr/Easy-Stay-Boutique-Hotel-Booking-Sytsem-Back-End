package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Booking;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingsByGuest_GuestId(Long guestId);

    List<Booking> findBookingsByBookingIdAndGuest_GuestId(Long bookingId, Long guestId);

    List<Booking> findBookingsByRoom_RoomNumber(Long roomNumber);

    @Query("SELECT b FROM Booking b WHERE b.guest.guestFirstName = :firstName AND b.guest.guestLastName = :lastName AND b.room.roomNumber = :roomNumber")
    Booking findByGuestFirstNameAndGuestLastNameAndRoomNumber(@Param("firstName") String firstName,
                                                              @Param("lastName") String lastName,
                                                              @Param("roomNumber") long roomNumber);
}
