package com.example.todolist.model.card;

import com.example.todolist.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CardRepository extends CrudRepository<Card, Long> {
    ArrayList<Card> findByUserCardOwner(User user);
    ArrayList<Card> findByBlockerCard(Card card);
}
