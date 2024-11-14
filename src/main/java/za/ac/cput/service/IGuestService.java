package za.ac.cput.service;

import za.ac.cput.domain.Guest;

import java.util.List;

public interface IGuestService {
    Guest saveGuest(Guest guest);
    Guest updateGuest(Guest guest);
    void deleteGuest(Long id);
    Guest findGuestById(long id);
    List<Guest> findAllGuests(); // New method
}
//    List<Guest> findGuestBookingByBookingId(Long bookingId);
//    Guest findBookingId(Long bookingId);

