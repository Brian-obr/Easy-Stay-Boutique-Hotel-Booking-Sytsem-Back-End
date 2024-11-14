package za.ac.cput.service;

import za.ac.cput.domain.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);
    User findUserByUsername(String username);
    void deleteUserByUsername(String username);
    User updateUser(User user);
    void deleteUserByUserId(Long userId);
    List<User> findAllUsers();
//    int updateUserByUsername(String username, User user);
    User findUserByUsernameAndPassword(String username, String password);
    User findUserById(long id);
}
