 package za.ac.cput.controller;

import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.User;
import za.ac.cput.service.impl.UserService;

import java.net.Authenticator;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findUserById(@PathVariable long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findUserByUsernameAndPassword/{username}/{password}")
    public User findUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        User userExists = userService.findUserByUsernameAndPassword(username, password);
        return userExists;
    }

    @GetMapping("/find")
    public ResponseEntity<User> findUserByUsername(@RequestParam String username) {
        User user = userService.findUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{username}")
public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
    userService.deleteUserByUsername(username);
    return ResponseEntity.noContent().build();
}

    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('MANAGER')")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User loginUser) {
//        boolean isAuthenticated = userService.authenticateUser(loginUser.getUserName(), loginUser.getPassword());
//        if (isAuthenticated) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//    }



}
