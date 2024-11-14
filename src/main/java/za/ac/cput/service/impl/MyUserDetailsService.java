package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.User;
import za.ac.cput.domain.UserPrincipal;
import za.ac.cput.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("User: " + user);
        return new UserPrincipal(user);
    }

}
