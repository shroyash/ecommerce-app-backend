package com.shroyash.multi_shop.service.implement;


import com.shroyash.multi_shop.model.User;
import com.shroyash.multi_shop.repository.UserRepository;
import com.shroyash.multi_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User registerUser(User user) {
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    // add more methods like createUser, updateUser, deleteUser, etc.
}
