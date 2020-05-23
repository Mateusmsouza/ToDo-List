package com.example.todolist.model.card;

import com.example.todolist.model.user.User;

import java.util.ArrayList;
import java.util.Optional;

public interface CardService {
    public ArrayList<Card> listAllCards(String username);
    public Optional<Card> getCardById(Long id);
    public Card createOrUpdate(Card card, String username);
    public void delete(Long id);
}
