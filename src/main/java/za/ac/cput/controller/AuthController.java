package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;
import za.ac.cput.service.impl.MyUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final MyUserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(MyUserDetailsService myUserDetailsService, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) throws Exception {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Fetch the user details using the username
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User authenticatedUser = userRepository.findByUserName(userDetails.getUsername());

        System.out.println("Authenticated user: " + authenticatedUser);

        if (authenticatedUser == null) {
            throw new UsernameNotFoundException("User not found after authentication");
        }

        // Return the authenticated User object (not UserPrincipal or UserDetails)
        return ResponseEntity.ok(authenticatedUser);

    }
}
