package com.example.todolist.controller;

import com.example.todolist.model.user.User;
import com.example.todolist.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> save(
            @RequestBody User user){
        try{
            user = userService.createOrUpdate(user, "ROLE_CUSTOMER");
            return new ResponseEntity<User>(
                    user,
                    HttpStatus.CREATED
            );

        } catch (DataIntegrityViolationException error) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Email or Username already taken by an account."
            );
        }
    }

    @DeleteMapping("/user/{userId}")
    public  ResponseEntity<User> removeUser(
            @PathVariable Long userId){
        userService.delete(userId);

        return new ResponseEntity<User>(
                HttpStatus.OK
        );
    }
}
