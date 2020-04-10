package com.example.todolist.controller;

import javax.servlet.http.HttpServletResponse;

import com.example.todolist.model.user.User;
import com.example.todolist.security.JwtUtils;
import com.example.todolist.security.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private AuthenticationManager auth;

    public void setAuth(AuthenticationManager auth) {
        this.auth = auth;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User login(@RequestBody Login login, HttpServletResponse response) throws JsonProcessingException {
        Authentication credentials = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        User user = (User) auth.authenticate(credentials).getPrincipal();
        user.setPassword(null);
        response.setHeader("Token", JwtUtils.generateToken(user));
        return user;
    }

}
