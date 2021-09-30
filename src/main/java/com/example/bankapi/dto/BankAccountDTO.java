package com.example.bankapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BankAccountDTO {
    long balance;

    @JsonProperty("user_id")
    long userId;

    public BankAccountDTO(long balance, long userId) {
        this.balance = balance;
        this.userId = userId;
    }

    public BankAccountDTO() {

    }
}
