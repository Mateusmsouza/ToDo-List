package com.example.todolist.model;

import java.util.ArrayList;
import java.util.Optional;

public interface UserService {
    public User createOrUpdate(User user);
    public Optional<User> login(User user);
    public void delete(Long id);
}
