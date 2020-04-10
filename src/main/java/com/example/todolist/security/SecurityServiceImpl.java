package com.example.todolist.security;

import com.example.todolist.model.user.User;
import com.example.todolist.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("securityService")
public class SecurityServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepo;



    /**
     * @param usuarioRepo the usuarioRepo to set
     */
    public void setUserRepo(UserRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usuarioRepo.findByName(username);
//        BCryptPasswordEncoder encoder = passwordEncoder();

        if(user.isPresent() == false) {
            throw new UsernameNotFoundException(username);
        }
        // set password
        User userFound = user.get();
//        userFound.setPassword(encoder.encode(userFound.getPassword()));
        return userFound;
    }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
