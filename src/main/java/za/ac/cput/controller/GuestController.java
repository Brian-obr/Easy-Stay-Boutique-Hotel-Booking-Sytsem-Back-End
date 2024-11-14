package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Guest;
import za.ac.cput.service.IGuestService;

import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestController {

    private IGuestService guestService;

    @Autowired
    public GuestController(IGuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable("id") long id) {
        Guest guest = guestService.findGuestById(id);
        return ResponseEntity.ok(guest);
    }

    @GetMapping("/all") // New endpoint to retrieve all guests
    public ResponseEntity<List<Guest>> getAllGuests() {
        List<Guest> guests = guestService.findAllGuests(); // Ensure this method exists in your service
        return ResponseEntity.ok(guests);
    }

    @PostMapping("/create")
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        Guest createdGuest = guestService.saveGuest(guest);
        return ResponseEntity.ok(createdGuest);
    }

    @PutMapping("/update")
    public ResponseEntity<Guest> updateGuest(@RequestBody Guest guest) {
        Guest updatedGuest = guestService.updateGuest(guest);
        return ResponseEntity.ok(updatedGuest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable("id") Long id) {
        guestService.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }
}