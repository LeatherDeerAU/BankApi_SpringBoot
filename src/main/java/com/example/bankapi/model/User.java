package com.example.bankapi.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;

    @Column(unique = true)
    private String number;

    public User(String first_name, String last_name, String number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.number = number;
    }

    public User() {

    }

}
