package za.ac.cput.service;

import za.ac.cput.domain.Booking;

import java.util.List;

public interface IBookingService {
    Booking createBooking(Booking booking);
    List<Booking> findAllBookings();
    Booking findBookingById(long id);
    Booking updateBooking(Booking booking);
    void deleteBooking(long id);
    List<Booking> findBookingsByBookingIdAndGuestId(long bookingId, long guestId);
    List<Booking> findBookingsByGuestId(long guestId);
    List<Booking> findBookingsByRoomNumber(long roomNumber);
}
