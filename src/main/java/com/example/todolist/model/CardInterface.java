package com.example.todolist.model;

import org.springframework.data.repository.CrudRepository;

public interface CardInterface extends CrudRepository<Card, Long> {
}
