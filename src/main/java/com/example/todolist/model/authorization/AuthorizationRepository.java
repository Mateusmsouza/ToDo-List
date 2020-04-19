package com.example.todolist.model.authorization;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorizationRepository extends CrudRepository<Authorization, Long> {
    public Optional<Authorization> findByAuthority(String authorization);
}
