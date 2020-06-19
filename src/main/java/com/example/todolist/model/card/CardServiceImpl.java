package com.example.todolist.model.card;

import com.example.todolist.model.authorization.Authorization;
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
    @PreAuthorize("hasAnyRole('ROLE_GOD', 'ROLE_CUSTOMER')")
    public ArrayList<Card> listAllCards(String username) {
        Optional<User> userQuery = userRepo.findByName(username);

        if(userQuery.isPresent()) {
            User user = userQuery.get();
            if (userAdmin(user)){
                return (ArrayList<Card>) cardRepo.findAll();
            }
            return (ArrayList<Card>) cardRepo.findByUserCardOwner(userQuery.get());
        }
        return new ArrayList<Card>();
    }

    private Boolean userAdmin(User user) {
        for(Authorization authorization: user.getAuthorizations()){
            if(authorization.getAuthority().contains("ROLE_GOD")){
                return true;
            }
        }
        return false;
    }
    @Override
    @PreAuthorize("hasAnyRole('ROLE_GOD', 'ROLE_CUSTOMER')")
    public Optional<Card> getCardById(Long cardId) {
        return cardRepo.findById(cardId);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_GOD', 'ROLE_CUSTOMER')")
    public Card createOrUpdate(Card card, String username){

        if (card.getBlockerCard() != null){
            this.getCardById(card.getBlockerCard().getId()).get();
        }

        User userFound = userRepo.findByName(username).get();
        card.setUserCardOwner(userFound);

        return cardRepo.save(card);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_GOD', 'ROLE_CUSTOMER')")
    public void delete(Long id) {
        Card card = this.getCardById(id).get();
        ArrayList<Card> cardsBlocked = cardRepo.findByBlockerCard(card);

        if (!cardsBlocked.isEmpty()){
            throw new IllegalArgumentException("Can't delete card that blocks others");
        }

        cardRepo.deleteById(id);
    }
}
