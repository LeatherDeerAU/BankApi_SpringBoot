package com.example.bankapi.controller;

import com.example.bankapi.dto.BankAccountDTO;
import com.example.bankapi.dto.TransferDTO;
import com.example.bankapi.dto.UpdateBalanceDTO;
import com.example.bankapi.model.BankAccount;
import com.example.bankapi.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("/api/bank_account")
public class BankAccountController {
    final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/{id}")
    BankAccount test(@PathVariable long id) {
        return bankAccountService.get(id);
    }

    @PostMapping("/new")
    public Map save(@RequestBody BankAccountDTO bankAccount_dto) {
        return Collections.singletonMap("id", bankAccountService.save(bankAccount_dto));
    }

    @PatchMapping("/update/{id}")
    public void updateBalance(@RequestBody UpdateBalanceDTO upd, @PathVariable long id) {
        bankAccountService.updateBalance(upd.getInc(), id);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferDTO req) {
        bankAccountService.transfer(req.getIdFrom(), req.getIdTo(), req.getAmount());
    }
}
