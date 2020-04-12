package com.example.todolist.security.services;

import com.example.todolist.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import com.example.todolist.model.user.User;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userSearch = userRepository.findByName(userName);

        if (userSearch.isPresent() == false){
            throw new UsernameNotFoundException("User not found: " + userName);
        }

        User userFound = userSearch.get();
        //userFound.setPassword(null);

        return userFound;
    }
}
