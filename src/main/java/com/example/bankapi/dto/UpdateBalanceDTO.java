package com.example.bankapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBalanceDTO {
    private long inc;

    public UpdateBalanceDTO(long inc) {
        this.inc = inc;
    }

}
