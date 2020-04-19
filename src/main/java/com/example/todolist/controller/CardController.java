package com.example.todolist.controller;

import com.example.todolist.model.card.Card;
import com.example.todolist.model.card.CardService;
import com.example.todolist.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@CrossOrigin
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping("/cards")
    @JsonView(View.CardRestAPI.class)
    public ResponseEntity<Collection<Card>> listCards(){
        return new ResponseEntity<Collection<Card>>(cardService.listAllCards(), HttpStatus.OK);
    }

    @RequestMapping("/cards/{cardId}")
    @JsonView(View.CardRestAPI.class)
    public ResponseEntity<Card> getCard(
            @PathVariable("cardId") Long cardId){

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
            @RequestBody Card card,
            UriComponentsBuilder uriComponentsBuilder){

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
            @RequestBody Card card,
            UriComponentsBuilder uriComponentsBuilder) {
        card = cardService.createOrUpdate(card, authentication.getName());
        return new ResponseEntity<Card>(
                card,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/card/{cardId}")
    @JsonView(View.CardRestAPI.class)
    public ResponseEntity<Card> delete(
            @PathVariable("cardId") Long cardId,
            UriComponentsBuilder uriComponentsBuilder){
        cardService.delete(cardId);
        return new ResponseEntity<Card>(
                    HttpStatus.OK
        );
        }
    }

