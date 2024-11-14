package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Role;
import za.ac.cput.domain.User;
import za.ac.cput.repository.RoleRepository;
import za.ac.cput.repository.UserRepository;
import za.ac.cput.service.IUserService;

import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(User user) {
        // Check if userName already exists
        User existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser != null) {
            throw new IllegalArgumentException("Username '" + user.getUserName() + "' is already taken.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }



    @Override
    public User findUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);

        if(user == null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User not found");
        }
        return userRepository.findByUserName(username);
    }

    @Override
    public void deleteUserByUsername(String username) {
        userRepository.deleteByUserName(username);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

//    public int updateUserByUsername(String username, User updatedUser) {
//        return userRepository.updateUserByUsername(
//                username,
//                updatedUser.getFirstName(),
//                updatedUser.getLastName(),
//                updatedUser.getPassword(),
//                updatedUser.getRole()
//        );
//    }


    @Override
    public void deleteUserByUserId(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUserNameAndPassword(username, password);
        return user;
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

//    public boolean authenticateUser(String username, String password) {
//        User user = userRepository.findByUserName(username);
//        if (user != null && user.getPassword().equals(password)) {
//            return true;
//        }
//        return false;
//    }
}
