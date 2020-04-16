package com.example.todolist.model.user;

import com.example.todolist.model.authorization.Authorization;
import com.example.todolist.model.authorization.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Override
    public User createOrUpdate(User user, String authorizationName) {
        encryptUserPassword(user);
        addAuthorizationToUser(user, authorizationName);

        return userRepository.save(user);
    }

    private void addAuthorizationToUser(User user, String authorizationName) {
        if (user.getAuthorities() == null || user.getAuthorities().size() < 1){
            Optional<Authorization> authorizationFound = authorizationRepository.findByAuthorizationName(authorizationName);

            if (authorizationFound.isPresent()){
                List<Authorization> authorizationList = new ArrayList<Authorization>();
                authorizationList.add(authorizationFound.get());

                user.setAuthorizations(authorizationList);

            }
        }
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
