package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Guest;
import za.ac.cput.repository.GuestRepository;
import za.ac.cput.service.IGuestService;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService implements IGuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Guest updateGuest(Guest guest) {
        // Check if the guest exists before updating
        if (guestRepository.existsById(guest.getGuestId())) {
            return guestRepository.save(guest);
        }
        // Optionally handle the case where the guest does not exist
        throw new RuntimeException("Guest not found");
    }

    @Override
    public Guest findGuestById(long id) {
        return guestRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteGuest(Long id) {
        // Check if the guest exists before deleting
        if (guestRepository.existsById(id)) {
            guestRepository.deleteById(id);
        } else {
            // Handle the case where the guest does not exist
            throw new RuntimeException("Guest not found");
        }
    }


    @Override
    public List<Guest> findAllGuests() {
        return guestRepository.findAll();
    }
//    @Override
//    public List<Guest> findGuestBookingByBookingId(Long bookingId) {
//        return guestRepository.findGuestBookingBybookingId(bookingId);
//    }

//    @Override
//    public Guest findBookingId(Long bookingId) {
//        Optional<Guest> guest = guestRepository.findById(bookingId);
//        return guest.orElseThrow(() -> new RuntimeException("Guest not found with booking ID: " + bookingId));
//    }
}
