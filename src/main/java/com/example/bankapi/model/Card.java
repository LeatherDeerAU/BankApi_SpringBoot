package com.example.bankapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    String number;

    @ManyToOne
    BankAccount bank_account;

    public Card(String number, BankAccount bank_account) {
        this.number = number;
        this.bank_account = bank_account;
    }

    public Card() {

    }
}
