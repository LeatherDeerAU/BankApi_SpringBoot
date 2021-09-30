package com.example.bankapi.DTO;

import lombok.Data;

@Data
public class CardDTO {
    String number;
    long bank_account_id;

    public CardDTO(String number, long bank_account_id) {
        this.number = number;
        this.bank_account_id = bank_account_id;
    }

    public CardDTO() {

    }
}
