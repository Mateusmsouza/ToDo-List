package com.example.todolist.model.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password);
}
