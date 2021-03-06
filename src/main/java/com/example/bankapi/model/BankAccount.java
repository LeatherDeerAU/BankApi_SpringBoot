package com.example.bankapi.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long balance;

    @ManyToOne
    private User user;

    public BankAccount(long balance, User user) {
        this.balance = balance;
        this.user = user;
    }

    public BankAccount() {

    }
}
