package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Guest;
import za.ac.cput.factory.GuestFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class GuestServiceTest {

    @Autowired
    private GuestService guestService;

    private static Guest guest3;
    private static Guest guest4;

    @BeforeAll
    static void setUp() {
        System.out.println("----------------------------Setup---------------------------------------------");

        // Setup Guests
        Guest guest1 = GuestFactory.buildGuestWithoutId("John", "Doe", LocalDate.of(1990, 1, 1), "Male",
                "123 Main St", "Suburb", "City", "12345", "Country", "0812345678", "contact1@example.com");
        Guest guest2 = GuestFactory.buildGuestWithoutId("Jane", "Doe", LocalDate.of(1992, 2, 2), "Female",
                "456 Elm St", "Suburb", "City", "67890", "Country", "0727654321", "contact2@example.com");
        guest3 = GuestFactory.buildGuestWithoutId("Jim", "Beam", LocalDate.of(1985, 5, 5), "Male",
                "123 Main St", "Suburb", "City", "12345", "Country", "0812345678", "contact1@example.com");
        guest4 = GuestFactory.buildGuestWithoutId("Jack", "Daniels", LocalDate.of(1988, 8, 8), "Male",
                "456 Elm St", "Suburb", "City", "67890", "Country", "0727654321", "contact2@example.com");

        System.out.println("----------------------------Successful-------------------------------------------");
    }

    @Test
    void a_saveGuest() {
        Guest createdGuest1 = guestService.saveGuest(guest3);
        assertNotNull(createdGuest1);
        System.out.println("----------------------------Guest: " + createdGuest1.getGuestId() + " created-----------------------------------" + "\n" + createdGuest1);

        Guest createdGuest2 = guestService.saveGuest(guest4);
        assertNotNull(createdGuest2);
        System.out.println("----------------------------Guest: " + createdGuest2.getGuestId() + " created-----------------------------------" + "\n" + createdGuest2);
    }

    @Test
    void b_findGuestById() {
        Guest foundGuest = guestService.findGuestById(guest3.getGuestId());
        assertNotNull(foundGuest);
        assertEquals(guest3.getGuestId(), foundGuest.getGuestId());
        System.out.println("---------------------------Read: " + foundGuest.getGuestId() + "-----------------------------------------------" + "\n" + foundGuest);
    }

    @Test
    void c_updateGuest() {
        Guest updatedGuest = new Guest.Builder().copy(guest3).setGuestGender("Non-Binary").build();
        Guest updated = guestService.updateGuest(updatedGuest);
        assertNotNull(updated);
        assertEquals("Non-Binary", updated.getGuestGender());
        System.out.println("---------------------------Guest: " + updated.getGuestId() + " Updated-----------------------------------------------" + "\n" + updated);
    }

    @Test
    void d_deleteGuest() {
        guestService.deleteGuest(guest3.getGuestId());
        Guest deletedGuest = guestService.findGuestById(guest3.getGuestId());
        assertNull(deletedGuest);
        System.out.println("---------------------------Deleted-----------------------------------------------" + "\n" + "Guest with ID " + guest3.getGuestId() + " deleted");
    }

}
