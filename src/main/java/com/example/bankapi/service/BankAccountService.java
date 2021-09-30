package com.example.bankapi.service;

import com.example.bankapi.dto.BankAccountDTO;
import com.example.bankapi.model.BankAccount;
import com.example.bankapi.repository.BankAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;


    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public long save(BankAccountDTO bankAccount_dto) {
        try {
            return bankAccountRepository.save(bankAccount_dto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public BankAccount get(long id) {
        Optional<BankAccount> elem = Optional.ofNullable(bankAccountRepository.get(id));
        if (elem.isPresent()) {
            return elem.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public void updateBalance(long amount, long id) {
        Optional<BankAccount> elem = Optional.ofNullable(bankAccountRepository.get(id));
        if (elem.isPresent()) {
            BankAccount account = elem.get();
            account.setBalance(account.getBalance() + amount);
            bankAccountRepository.update(account);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public void transfer(long id_from, long id_to, long amount) {
        Optional<BankAccount> from = Optional.ofNullable(bankAccountRepository.get(id_from));
        Optional<BankAccount> to = Optional.ofNullable(bankAccountRepository.get(id_to));
        if (from.isEmpty() || to.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (from.get().getBalance() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Недостаточно средств");
        }

        updateBalance(-amount, id_from);
        updateBalance(amount, id_to);
    }
}
