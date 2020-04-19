package com.example.todolist.model.card;

import com.example.todolist.model.user.User;
import com.example.todolist.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service("CardService")
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    @PreAuthorize("isAuthenticated()")
    public ArrayList<Card> listAllCards() {
        return (ArrayList<Card>) cardRepo.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Optional<Card> getCardById(Long cardId) {
        return cardRepo.findById(cardId);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Card createOrUpdate(Card card, String username){

        if (card.getBlockerCard() != null){
            this.getCardById(card.getBlockerCard().getId()).get();
        }

        User userFound = userRepo.findByName(username).get();
        System.out.println(userFound);
        return cardRepo.save(card);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public void delete(Long id) {
        cardRepo.deleteById(id);
    }
}
