package com.example.bankapi.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBalanceDTO {
    long inc;

    public UpdateBalanceDTO(long inc) {
        this.inc = inc;
    }

}
