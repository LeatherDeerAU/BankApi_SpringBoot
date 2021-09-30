package com.example.bankapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BankAccountDTO {
    private long balance;

    @JsonProperty("user_id")
    private long userId;

    public BankAccountDTO(long balance, long userId) {
        this.balance = balance;
        this.userId = userId;
    }

    public BankAccountDTO() {

    }
}
