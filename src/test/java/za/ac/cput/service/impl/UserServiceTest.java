package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Role;
import za.ac.cput.domain.User;
import za.ac.cput.factory.RoleFactory;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.RoleRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    private static User user1;
    private static User user2;
    private static User user3;
    private static User user4;

    @BeforeAll
    static void setUp() {
        System.out.println("----------------------------Setup---------------------------------------------");
        Role roleManager = RoleFactory.buildRole("ROLE_MANAGER");

        Role roleReceptionist = RoleFactory.buildRole("ROLE_RECEPTIONIST");

        List<Role> managerRoleList = List.of(roleManager);
        List<Role> receptionistRoleList = List.of(roleReceptionist);

        // Reuse these roles for user creation
        user1 = UserFactory.buildUserWithoutId("Bobby", "Mokoena", "Me", "1", managerRoleList);
        user2 = UserFactory.buildUserWithoutId("Kat", "Van der boom", "user2", "1", receptionistRoleList);
        user3 = UserFactory.buildUserWithoutId("Daniel", "Mashishi", "user3", "1", receptionistRoleList);
        user4 = UserFactory.buildUserWithoutId("Martin", "Smith", "user4", "1", receptionistRoleList);

        System.out.println("----------------------------Successful-------------------------------------------");
    }


    @Test
    void b_saveUser_DuplicateUserName_ShouldThrowException() {
        // Try saving a user with an existing username
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(user2); // Assuming user2's username is already in the database
        });

        String expectedMessage = "Username 'user2' is already taken.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }



    @Test
    void a_createUser() {
        User createdUser1 = userService.saveUser(user1);
        assertNotNull(createdUser1);
        System.out.println("----------------------------User: " + createdUser1.getUserId() + " created-----------------------------------" + "\n" + createdUser1);

        User createdUser2 = userService.saveUser(user2);
        assertNotNull(createdUser2);
        System.out.println("----------------------------User: " + createdUser2.getUserId() + " created-----------------------------------" + "\n" + createdUser2);

        User createdUser3 = userService.saveUser(user3);
        assertNotNull(createdUser3);
        System.out.println("----------------------------User: " + createdUser3.getUserId() + " created-----------------------------------" + "\n" + createdUser3);

        User createdUser4 = userService.saveUser(user4);
        assertNotNull(createdUser4);
        System.out.println("----------------------------User: " + createdUser4.getUserId() + " created-----------------------------------" + "\n" + createdUser4);
    }

    @Test
    void c_findUserByUsername() {
        User readUser = userService.findUserByUsername(user1.getUserName());
        assertNotNull(readUser);
        assertEquals(user1.getUserName(), readUser.getUserName());
        System.out.println("---------------------------Read: " + readUser.getUserId() + "-----------------------------------------------" + "\n" + readUser);
    }

//    @Test
//    void c_updateUser() {
//        User updatedUser = new User.Builder().copy(user1).setPassword("newpassword1").build();
//        assertNotNull(updatedUser);
//        User updated = userService.updateUser(updatedUser);
//        assertNotNull(updated);
//        System.out.println("---------------------------User: " + updated.getUserId() + " Updated-----------------------------------------------" + "\n" + updated);
//    }
//    @Test
//    void d_updateUserByUsername(){
//        User updatedUser = new User.Builder().copy(user2).setPassword("newpassword2").build();
//        assertNotNull(updatedUser);
//        int updated = userService.updateUserByUsername(updatedUser.getUserName(), updatedUser);
//        assertNotNull(updated);
//    }
    @Test
    void f_findByUserNameAndPassword(){
        User readUser = userService.findUserByUsernameAndPassword(user1.getUserName(),user1.getPassword());
        assertNotNull(readUser);
        System.out.println(readUser);

    }
//    @Test
//    void g_deleteUser() {
//        userService.deleteUserByUserId(user1.getUserId());
//        System.out.println("---------------------------Deleted-----------------------------------------------" + "\n" + "User with ID " + user1.getUserId() + " deleted");
//    }
}
