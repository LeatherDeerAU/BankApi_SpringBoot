package com.example.bankapi.controller;

import com.example.bankapi.dto.CardDTO;
import com.example.bankapi.model.Card;
import com.example.bankapi.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/card")
public class CardController {
    final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{number}")
    Card get(@PathVariable String number) {
        return cardService.findByNumber(number);
    }

    @GetMapping("/all")
    List<Card> getAll() {
        return cardService.getAll();
    }

    @PostMapping("/new")
    Map add(@RequestBody CardDTO card) {
        return Collections.singletonMap("id", cardService.save(card.getNumber(), card.getBankAccountId()));
    }
}
