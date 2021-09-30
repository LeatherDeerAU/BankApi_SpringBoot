package com.example.bankapi.DTO;


import lombok.Data;

@Data
public class BankAccount_DTO {
    long balance;
    long user_id;

    public BankAccount_DTO(long balance, long user_id) {
        this.balance = balance;
        this.user_id = user_id;
    }

    public BankAccount_DTO() {

    }
}
