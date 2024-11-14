package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Guest;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByGuestFirstNameAndGuestLastName(String firstName, String lastName);

    List<Guest> findByGuestFirstName(String guestName);
}
