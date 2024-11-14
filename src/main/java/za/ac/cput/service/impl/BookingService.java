package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.repository.BookingRepository;
import za.ac.cput.service.IBookingService;

import java.util.List;

@Service
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public Booking createBooking(Booking booking) {
        try {
            return bookingRepository.save(booking);
        } catch (DataAccessException e) {
            throw new RuntimeException("Database access error while creating booking", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create booking", e);
        }
    }


    @Override
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findBookingById(long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Booking updateBooking(Booking booking) {
        try {
            return bookingRepository.save(booking);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update booking", e); // Handle the exception
        }
    }

    @Override
    @Transactional
    public void deleteBooking(long id) {
        try {
            bookingRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete booking", e); // Handle the exception
        }
    }

    @Override
    public List<Booking> findBookingsByBookingIdAndGuestId(long bookingId, long guestId) {
        return bookingRepository.findBookingsByBookingIdAndGuest_GuestId(bookingId, guestId);
    }

    @Override
    public List<Booking> findBookingsByGuestId(long guestId) {
        return bookingRepository.findBookingsByGuest_GuestId(guestId);
    }

    @Override
    public List<Booking> findBookingsByRoomNumber(long roomNumber) {
        return bookingRepository.findBookingsByRoom_RoomNumber(roomNumber);
    }

    @Transactional
    public void checkOut(String guestFirstName, String guestLastName, long roomNumber) {
        Booking booking = bookingRepository.findByGuestFirstNameAndGuestLastNameAndRoomNumber(guestFirstName, guestLastName, roomNumber);
        if (booking != null) {
            bookingRepository.delete(booking);
        } else {
            throw new RuntimeException("Booking not found for the given guest and room number");
        }
    }

}
