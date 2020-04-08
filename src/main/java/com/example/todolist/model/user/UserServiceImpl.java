package com.example.todolist.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> login(User user) {
        return userRepository.findByEmailIgnoreCaseAndPassword(
                user.getEmail(),
                user.getPassword()
        );
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
