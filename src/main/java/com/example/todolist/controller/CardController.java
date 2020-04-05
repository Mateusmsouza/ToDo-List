package com.example.todolist.controller;

import com.example.todolist.model.Card;
import com.example.todolist.model.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@CrossOrigin
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping("/cards")
    public ResponseEntity<Collection<Card>> listCards(){
        return new ResponseEntity<Collection<Card>>(cardService.listAllCards(), HttpStatus.OK);
    }

    @RequestMapping("/cards/{cardId}")
    public ResponseEntity<Card> getCard(
            @PathVariable("cardId") Long cardId){

        Card card = cardService.getCardById(cardId).get();
        return new ResponseEntity<Card>(
                card,
                HttpStatus.OK
        );
    }

    @PostMapping("/card")
    public ResponseEntity<Card> save(
            @RequestBody Card card,
            UriComponentsBuilder uriComponentsBuilder){
        card = cardService.createOrUpdate(card);
        //HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<Card>(
                card,
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/card")
    public ResponseEntity<Card> update(
            @RequestBody Card card,
            UriComponentsBuilder uriComponentsBuilder) {
        card = cardService.createOrUpdate(card);
        //HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<Card>(
                card,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/card/{cardId}")
    public ResponseEntity<Card> delete(
            @PathVariable("cardId") Long cardId,
            UriComponentsBuilder uriComponentsBuilder){
        cardService.delete(cardId);
        //HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<Card>(
                    HttpStatus.OK
        );
        }
    }

