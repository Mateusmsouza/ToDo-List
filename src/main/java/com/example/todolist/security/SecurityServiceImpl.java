package com.example.todolist.security;

import com.example.todolist.model.user.User;
import com.example.todolist.model.user.UserRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("segurancaService")
public class SecurityServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepo;

    /**
     * @param usuarioRepo the usuarioRepo to set
     */
    public void setUsuarioRepo(UserRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usuarioRepo.findByName(username);

        if(user.isPresent() == false) {
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }
}
