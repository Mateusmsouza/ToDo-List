package com.example.todolist.model.user;

import java.util.Optional;

public interface UserService {
    public User createOrUpdate(User user, String authorizationName);
    public Optional<User> login(User user);
    public void delete(Long id);
}
