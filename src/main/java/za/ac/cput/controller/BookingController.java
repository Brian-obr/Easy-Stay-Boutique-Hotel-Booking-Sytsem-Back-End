package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.service.impl.BookingService;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        try {
            Booking createdBooking = bookingService.createBooking(booking);
            return ResponseEntity.ok(createdBooking);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> findAllBookings() {
        List<Booking> bookings = bookingService.findAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Booking> findBookingById(@PathVariable long id) {
        Booking booking = bookingService.findBookingById(id);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking) {
        try {
            Booking updatedBooking = bookingService.updateBooking(booking);
            return ResponseEntity.ok(updatedBooking);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable long id) {
        Booking booking = bookingService.findBookingById(id);
        if (booking != null) {
            bookingService.deleteBooking(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchGuest")
    public ResponseEntity<List<Booking>> findBookingsByBookingIdAndGuestId(
            @RequestParam long bookingId,
            @RequestParam long guestId) {
        List<Booking> bookings = bookingService.findBookingsByBookingIdAndGuestId(bookingId, guestId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<Booking>> findBookingsByGuestId(@PathVariable long guestId) {
        List<Booking> bookings = bookingService.findBookingsByGuestId(guestId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/searchRoom/{roomNumber}")
    public ResponseEntity<List<Booking>> findBookingsByRoomNumber(@PathVariable long roomNumber) {
        List<Booking> bookings = bookingService.findBookingsByRoomNumber(roomNumber);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkOut(@RequestParam String guestFirstName,
                                           @RequestParam String guestLastName,
                                           @RequestParam long roomNumber) {
        try {
            bookingService.checkOut(guestFirstName, guestLastName, roomNumber);
            return ResponseEntity.ok("Checkout successful");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
