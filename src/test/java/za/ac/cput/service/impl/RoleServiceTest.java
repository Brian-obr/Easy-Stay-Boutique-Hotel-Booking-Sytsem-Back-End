package za.ac.cput.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Role;
import za.ac.cput.factory.RoleFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class RoleServiceTest {
    @Autowired
    RoleService roleService;
    private static Role role1;
    private static Role role2;
    @BeforeAll
    static void setUp(){
        role1 = RoleFactory.buildRole("ROLE_MANAGER");
        role2 = RoleFactory.buildRole("ROLE_RECEPTIONIST");
    }
    @Test
    void registerRole() {
        roleService.registerRole(role1);
        assertNotNull(role1);
        System.out.println(role1);
        roleService.registerRole(role2);
        assertNotNull(role2);
        System.out.println(role2);
    }

//    @Test
//    void updateRole() {
//
//    }
//
//    @Test
//    void deleteRole() {
//    }
//
//    @Test
//    void getAllRoles() {
//    }
}