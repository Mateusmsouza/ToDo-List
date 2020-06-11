package com.example.todolist.model.card;

import java.util.ArrayList;
import java.util.Optional;

public interface CardService {
    ArrayList<Card> listAllCards(String username);
    Optional<Card> getCardById(Long id);
    Card createOrUpdate(Card card, String username);
    void delete(Long id);
}
