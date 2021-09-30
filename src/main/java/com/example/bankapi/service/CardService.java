package com.example.bankapi.service;

import com.example.bankapi.model.Card;
import com.example.bankapi.repository.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public long save(String number, long bankAccountId) {
        if (cardRepository.findByNumber(number) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "КАРТА УЖЕ ДОБАВЛЕНА");
        }

        try {
            return cardRepository.save(number, bankAccountId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public Card findByNumber(String number) {
        Optional<Card> elem = Optional.ofNullable(cardRepository.findByNumber(number));
        if (elem.isPresent()) {
            return elem.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Карта не найдена");
        }
    }

    public List<Card> getAll() {
        return cardRepository.getAll();
    }
}
