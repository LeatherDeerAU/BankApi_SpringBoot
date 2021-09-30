package com.example.bankapi.DTO;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDTO {
    String first_name;
    String last_name;
    String number;

    public UserDTO(String first_name, String last_name, String number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.number = number;
    }

    public UserDTO() {

    }
}
