package com.example.todolist.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Service("CardService")
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public ArrayList<Card> listAllCards() {
        return (ArrayList<Card>) cardRepo.findAll();
    }

    @Override
    public Optional<Card> getCardById(Long cardId) {
        return cardRepo.findById(cardId);
    }

    @Override
    public Card createOrUpdate(Card card){

        if (card.getBlockerCard() != null){
            this.getCardById(card.getBlockerCard().getId()).get();
        }

        if (card.getUserCardOwner() != null){
            this.userRepo.findById(card.getUserCardOwner().getId()).get();
        }

        return cardRepo.save(card);
    }

    @Override
    public void delete(Long id) {
        cardRepo.deleteById(id);
    }
}
