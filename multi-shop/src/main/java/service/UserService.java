package service;

import model.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User registerUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User getUserByEmail(String email); // Useful for login/authentication

    boolean existsByEmail(String email);
}
