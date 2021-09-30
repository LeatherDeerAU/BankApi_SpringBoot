package com.example.bankapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CardDTO {
    String number;
    @JsonProperty("bank_account_id")
    long bankAccountId;

    public CardDTO(String number, long bankAccountId) {
        this.number = number;
        this.bankAccountId = bankAccountId;
    }

    public CardDTO() {

    }
}
