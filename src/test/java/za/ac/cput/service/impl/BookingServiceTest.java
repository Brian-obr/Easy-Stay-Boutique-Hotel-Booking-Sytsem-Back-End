package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.RoomType;
import za.ac.cput.factory.*;
import za.ac.cput.service.impl.BookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    private static Booking booking1;
    private static Booking booking2;
    private static Booking booking3;

    @BeforeAll
    static void setUp() {
        System.out.println("----------------------------Setup---------------------------------------------");

        // Setup Guests
        Guest guest1 = GuestFactory.buildGuestWithoutId("John", "Doe", LocalDate.of(1990, 1, 1), "Male",
                "123 Main St", "Suburb", "City", "12345", "Country", "0812345678", "contact1@example.com");
        Guest guest2 = GuestFactory.buildGuestWithoutId("Jane", "Doe", LocalDate.of(1992, 2, 2), "Female",
                "456 Elm St", "Suburb", "City", "67890", "Country", "0723456789","contact2@example.com" );

        // Setup Rooms
        Room room1 = RoomFactory.buildRoom(103L, 150.0,  RoomType.SINGLE);
        Room room2 = RoomFactory.buildRoom(104L, 200.0,  RoomType.DOUBLE);

        // Setup Bookings
        booking1 = BookingFactory.buildBookingWithoutId(LocalDateTime.now(), LocalDate.now(), LocalDate.now().plusDays(3), 450.0, guest1, room1);
        booking2 = BookingFactory.buildBookingWithoutId(LocalDateTime.now().minusDays(2), LocalDate.now(), LocalDate.now().plusDays(2), 400.0, guest2, room2);
        booking3 = BookingFactory.buildBookingWithoutId(LocalDateTime.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), 500.0, guest1, room2);
    }

    @Test
    void a_createBooking() {
        Booking createdBooking1 = bookingService.createBooking(booking1);
        assertNotNull(createdBooking1);
        System.out.println("Booking created: " + createdBooking1);

        Booking createdBooking2 = bookingService.createBooking(booking2);
        assertNotNull(createdBooking2);
        System.out.println("Booking created: " + createdBooking2);

    }

    @Test
    void b_findAllBookings() {
        List<Booking> bookings = bookingService.findAllBookings();
        assertFalse(bookings.isEmpty());
        System.out.println("All bookings: " + bookings);
    }

    @Test
    void c_findBookingById() {
        Booking foundBooking = bookingService.findBookingById(booking1.getBookingId());
        assertNotNull(foundBooking);
        assertEquals(booking1.getBookingId(), foundBooking.getBookingId());
        System.out.println("Booking found: " + foundBooking);
    }

    @Test
    void d_updateBooking() {
        Booking bookingUpdate = new Booking.Builder().copy(booking1).setTotalAmount(355).build();
        assertNotNull(bookingUpdate);
        System.out.println(bookingUpdate);
        Booking bookingChanged = bookingService.updateBooking(bookingUpdate);
        assertNotNull(bookingChanged);
        System.out.println(bookingChanged);
    }
    @Test
    void e_deleteBooking() {
//        bookingService.deleteBooking(booking2);
//        Booking deletedBooking = bookingService.findBookingById(booking2.getBookingId());
//        assertNull(deletedBooking);
//        System.out.println("Booking deleted: " + booking2.getBookingId());
    }

    @Test
    void f_findBookingsByBookingIdAndGuestId() {
        List<Booking> bookings = bookingService.findBookingsByBookingIdAndGuestId(booking1.getBookingId(), booking1.getGuest().getGuestId());
        assertFalse(bookings.isEmpty());
        System.out.println("Bookings found by bookingId and guestId: " + bookings);
    }

    @Test
    void g_findBookingsByGuestId() {
        List<Booking> bookings = bookingService.findBookingsByGuestId(booking1.getGuest().getGuestId());
        assertFalse(bookings.isEmpty());
        System.out.println("Bookings found by guestId: " + bookings);
    }

    @Test
    void h_findBookingsByRoomNumber() {
        long roomNumber = booking1.getRoom().getRoomNumber();
        List<Booking> bookings = bookingService.findBookingsByRoomNumber(roomNumber);
        assertFalse(bookings.isEmpty(), "No bookings found for room number: " + booking1.getRoom().getRoomNumber());
        System.out.println("Bookings found by room number: " + bookings);
    }

    @Test
    void i_checkOut() {
        // Perform checkout
        bookingService.checkOut(booking1.getGuest().getGuestFirstName(), booking1.getGuest().getGuestLastName(), booking1.getRoom().getRoomNumber());

        // Verify the booking is deleted
        Booking deletedBooking = bookingService.findBookingById(booking1.getBookingId());
        assertNull(deletedBooking, "Booking should be deleted after checkout");
        System.out.println("Booking checked out and deleted: " + booking1.getBookingId());
    }
}
