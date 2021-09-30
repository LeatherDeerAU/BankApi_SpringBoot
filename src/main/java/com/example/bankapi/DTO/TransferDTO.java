package com.example.bankapi.DTO;

import lombok.Data;

@Data
public class TransferDTO {
    long id_from;
    long id_to;
    long amount;

    public TransferDTO(long id_from, long id_to, long amount) {
        this.id_from = id_from;
        this.id_to = id_to;
        this.amount = amount;
    }

    public TransferDTO() {

    }
}
