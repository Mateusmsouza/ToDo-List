package com.example.todolist.model.card;

import com.example.todolist.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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

        //TODO: fix here to retrieve user data from authorization token
        //currently @JsonIgnoreField on model is breaking below code
        if (card.getUserCardOwner() != null){
            card.setUserCardOwner(
                    userRepo.findById(
                            card.getUserCardOwner().getId()
                    ).get()
            );
        }

        return cardRepo.save(card);
    }

    @Override
    public void delete(Long id) {
        cardRepo.deleteById(id);
    }
}
