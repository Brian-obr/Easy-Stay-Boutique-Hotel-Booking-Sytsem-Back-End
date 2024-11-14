package za.ac.cput.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.PaymentMethod;
import za.ac.cput.domain.enums.PaymentStatus;
import za.ac.cput.domain.enums.RoomType;
import za.ac.cput.factory.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Ensure transactions are managed during the test
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    private static Payment payment1;
    private static Payment payment2;
    private static Payment payment3;

    @BeforeAll
    static void setUp() {
        System.out.println("----------------------------Setup---------------------------------------------");

        // Setup Guests
        Guest guest1 = GuestFactory.buildGuestWithoutId("Lerato", "Express", LocalDate.of(1983, 10, 8), "Male", "456 Elm St", "Suburb", "City", "67890", "Country", "0727654321", "contact2@example.com");
        Guest guest2 = GuestFactory.buildGuestWithoutId("Jane", "Doe", LocalDate.of(1992, 2, 2), "Female", "456 Elm St", "Suburb", "City", "67890", "Country", "0727654321", "contact2@example.com");

        // Setup Rooms
        Room room1 = RoomFactory.buildRoom(105L, 150.0,  RoomType.SINGLE);
        Room room2 = RoomFactory.buildRoom(106L, 200.0,  RoomType.DOUBLE);

        // Setup Bookings
        Booking booking1 = BookingFactory.buildBookingWithoutId(LocalDateTime.now(), LocalDate.now(), LocalDate.now().plusDays(3), 450.0, guest1, room1);
        Booking booking2 = BookingFactory.buildBookingWithoutId(LocalDateTime.now().minusHours(4), LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), 500.0, guest1, room2);
        Booking booking3 = BookingFactory.buildBookingWithoutId(LocalDateTime.now().minusDays(4), LocalDate.now().plusDays(4), LocalDate.now().plusDays(7), 300.0, guest2, room2);

        // Setup Payments
        payment1 = PaymentFactory.buildPaymentWithoutId("TXN123", LocalDateTime.now(), booking1, 450.0, PaymentStatus.COMPLETED, PaymentMethod.CREDIT_CARD);
        payment2 = PaymentFactory.buildPaymentWithoutId("TXN124", LocalDateTime.now().plusDays(1), booking1, 500.0, PaymentStatus.PENDING, PaymentMethod.EFT);
        payment3 = PaymentFactory.buildPaymentWithoutId("TXN125", LocalDateTime.now().plusDays(2), booking2, 550.0, PaymentStatus.FAILED, PaymentMethod.CASH);

        System.out.println("------------------------------------------Setup Successful---------------------------------------------");
    }

    @Test
    void save() {
        Payment savedPayment = paymentService.save(payment1);
        assertNotNull(savedPayment);
        assertEquals(payment1.getTransactionId(), savedPayment.getTransactionId());
        System.out.println(savedPayment);

        Payment savedPayment2 = paymentService.save(payment2);
        assertNotNull(savedPayment2);
        assertEquals(payment2.getTransactionId(), savedPayment2.getTransactionId());
        System.out.println(savedPayment2);

        Payment savedPayment3 = paymentService.save(payment3);
        assertNotNull(savedPayment3);
        assertEquals(payment3.getTransactionId(), savedPayment3.getTransactionId());
        System.out.println(savedPayment3);
    }
}
