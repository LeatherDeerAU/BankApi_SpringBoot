package com.example.bankapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransferDTO {
    @JsonProperty("id_from")
    private long idFrom;

    @JsonProperty("id_to")
    private long idTo;
    private long amount;

    public TransferDTO(long idFrom, long idTo, long amount) {
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.amount = amount;
    }

    public TransferDTO() {

    }
}
