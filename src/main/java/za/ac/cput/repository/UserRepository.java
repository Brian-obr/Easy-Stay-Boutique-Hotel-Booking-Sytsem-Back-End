package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Role;
import za.ac.cput.domain.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
    void deleteByUserName(String username);
//    @Modifying
//    @Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName, u.password = :password, u.role = :role WHERE u.userName = :username")
//    int updateUserByUsername(@Param("username") String username,
//                             @Param("firstName") String firstName,
//                             @Param("lastName") String lastName,
//                             @Param("password") String password,
//                             @Param("role") Role role);
    User findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);
}
