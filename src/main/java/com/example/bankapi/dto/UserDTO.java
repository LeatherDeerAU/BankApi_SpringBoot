package com.example.bankapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTO {
    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;
    String number;

    public UserDTO(String firstName, String lastName, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    public UserDTO() {

    }
}
