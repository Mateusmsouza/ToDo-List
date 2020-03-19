package com.example.todolist.model;

import java.util.ArrayList;
import java.util.Optional;

public interface CardService {
    public ArrayList<Card> listAllCards();
    public Optional<Card> getCardById(Long id);
    public Card createOrUpdate(Card card);
    public void delete(Long id);
}
