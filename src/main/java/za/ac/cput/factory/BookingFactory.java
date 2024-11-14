package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Guest;
import za.ac.cput.domain.Room;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class BookingFactory {

    // Factory method to create a Booking with all details
    public static Booking buildBooking(long bookingId, LocalDateTime bookingDate, LocalDate checkInDate, LocalDate checkOutDate,
                                       double totalAmount, Guest guest, Room room) {
        if (bookingId < 0 ||bookingDate== null || checkInDate == null || checkOutDate == null || totalAmount <= 0 || guest == null || room == null) {
            return null;
        }
        return new Booking.Builder()
                .setBookingId(bookingId)
                .setBookingDate(bookingDate)
                .setCheckInDate(checkInDate)
                .setCheckOutDate(checkOutDate)
                .setTotalAmount(totalAmount)
                .setGuest(guest)
                .setRoom(room)
                .build();
    }

    // Factory method to create a Booking without an ID
    public static Booking buildBookingWithoutId(LocalDateTime bookingDate, LocalDate checkInDate, LocalDate checkOutDate,
                                                Double totalAmount, Guest guest, Room room) {
        if (bookingDate == null || checkInDate == null || checkOutDate == null || totalAmount == null ||
                totalAmount <= 0 || guest == null || room == null) {
            return null;
        }
        return new Booking.Builder()
                .setBookingDate(bookingDate)
                .setCheckInDate(checkInDate)
                .setCheckOutDate(checkOutDate)
                .setTotalAmount(totalAmount)
                .setGuest(guest)
                .setRoom(room)
                .build();
    }
}
