package com.example.todolist.controller;

import com.example.todolist.model.card.Card;
import com.example.todolist.model.card.CardService;
import com.example.todolist.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/cards")
    @JsonView(View.CardRestAPI.class)
    public ResponseEntity<Collection<Card>> listCards(
            Authentication authentication
    ) {
        return new ResponseEntity<Collection<Card>>(cardService.listAllCards(authentication.getName()), HttpStatus.OK);
    }

    @GetMapping("/cards/{cardId}")
    @JsonView(View.CardRestAPI.class)
    public ResponseEntity<Card> getCard(
            @PathVariable("cardId") Long cardId) {

        Card card = cardService.getCardById(cardId).get();
        return new ResponseEntity<Card>(
                card,
                HttpStatus.OK
        );
    }

    @PostMapping("/card")
    @JsonView(View.CardRestAPI.class)
    public ResponseEntity<Card> save(
            Authentication authentication,
            @RequestBody Card card
    ) {
        card = cardService.createOrUpdate(card, authentication.getName());
        return new ResponseEntity<Card>(
                card,
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/card")
    @JsonView(View.CardRestAPI.class)
    public ResponseEntity<Card> update(
            Authentication authentication,
            @RequestBody Card card) {
        card = cardService.createOrUpdate(card, authentication.getName());
        return new ResponseEntity<Card>(
                card,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/card/{cardId}")
    @JsonView(View.CardRestAPI.class)
    public ResponseEntity<Card> delete(
            @PathVariable("cardId") Long cardId) {
        try {
            cardService.delete(cardId);
        } catch (IllegalArgumentException err) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Can't exclude a card that blocks another card."
            );
        } catch (NoSuchElementException err) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Card not found."
            );
        }
        return new ResponseEntity<Card>(
                HttpStatus.OK);
    }


}

