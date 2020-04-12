package com.example.todolist.controller;

import com.example.todolist.model.card.Card;
import com.example.todolist.model.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@CrossOrigin
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping("/cards")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Collection<Card>> listCards(){
        return new ResponseEntity<Collection<Card>>(cardService.listAllCards(), HttpStatus.OK);
    }

    @RequestMapping("/cards/{cardId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Card> getCard(
            @PathVariable("cardId") Long cardId){

        Card card = cardService.getCardById(cardId).get();
        return new ResponseEntity<Card>(
                card,
                HttpStatus.OK
        );
    }

    @PostMapping("/card")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Card> save(
            @RequestBody Card card,
            UriComponentsBuilder uriComponentsBuilder){
        card = cardService.createOrUpdate(card);
        return new ResponseEntity<Card>(
                card,
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/card")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Card> update(
            @RequestBody Card card,
            UriComponentsBuilder uriComponentsBuilder) {
        card = cardService.createOrUpdate(card);
        return new ResponseEntity<Card>(
                card,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/card/{cardId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Card> delete(
            @PathVariable("cardId") Long cardId,
            UriComponentsBuilder uriComponentsBuilder){
        cardService.delete(cardId);
        return new ResponseEntity<Card>(
                    HttpStatus.OK
        );
        }
    }

