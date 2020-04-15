package com.example.todolist.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User createOrUpdate(User user) {
        encryptUserPassword(user);
        return userRepository.save(user);
    }

    public Optional<User> login(User user) {
        return userRepository.findByEmailIgnoreCaseAndPassword(
                user.getEmail(),
                user.getPassword()
        );
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private void encryptUserPassword(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
    }
}
