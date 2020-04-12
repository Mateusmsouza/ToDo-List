package com.example.todolist.controller;

import javax.servlet.http.HttpServletResponse;

import com.example.todolist.model.user.User;
import com.example.todolist.security.utils.JwtUtil;
import com.example.todolist.security.utils.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager auth;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @CrossOrigin(exposedHeaders = "Token")
    public ResponseEntity<User> login(@RequestBody Login login, HttpServletResponse response) throws JsonProcessingException {

        Authentication credentials = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());

        User user = (User) auth.authenticate(credentials).getPrincipal();
        response.setHeader("token", new JwtUtil().generateToken(user));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


}
