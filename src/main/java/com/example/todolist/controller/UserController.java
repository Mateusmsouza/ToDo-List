//package com.example.todolist.controller;
//
//import com.example.todolist.model.user.User;
//import com.example.todolist.model.user.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//import java.util.Optional;
//
//@RestController
//@CrossOrigin
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/user")
//    public ResponseEntity<User> save(
//            @RequestBody User user,
//            UriComponentsBuilder uriComponentsBuilder){
//        user = userService.createOrUpdate(user);
//        //HttpHeaders responseHeaders = new HttpHeaders();
//        return new ResponseEntity<User>(
//                user,
//                HttpStatus.CREATED
//        );
//    }
//
//    @PostMapping("/user/login")
//    public ResponseEntity<User> login(
//            @RequestBody User user,
//            UriComponentsBuilder uriComponentsBuilder){
//
//        Optional<User> foundUser = userService.login(user);
//
//
//        if( foundUser.isPresent() == false){
//            return new ResponseEntity<User>(
//                    HttpStatus.UNAUTHORIZED
//            );
//        }
//
//        return new ResponseEntity<User>(
//                foundUser.get(),
//                HttpStatus.OK
//        );
//    }
//
//    @DeleteMapping("/user/{userId}")
//    public  ResponseEntity<User> removeUser(
//            @PathVariable Long userId){
//        userService.delete(userId);
//
//        return new ResponseEntity<User>(
//                HttpStatus.OK
//        );
//    }
//}
