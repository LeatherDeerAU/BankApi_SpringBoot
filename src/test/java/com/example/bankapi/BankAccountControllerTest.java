package com.example.bankapi;


import com.example.bankapi.dto.BankAccountDTO;
import com.example.bankapi.dto.UpdateBalanceDTO;
import com.example.bankapi.dto.UserDTO;
import com.example.bankapi.model.BankAccount;
import com.example.bankapi.service.BankAccountService;
import com.example.bankapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankAccountControllerTest {
    private final static ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    UserService userService;

    @Test
    void getNoExistEntity() throws Exception {
        mockMvc.perform(get("/api/bank_account/-1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void saveBankAccountOK() throws Exception {
        userService.save(new UserDTO("firName", "lasName", "0001"));
        String contentAsString = mockMvc.perform(post("/api/bank_account/new")
                        .content(om.writeValueAsString(new BankAccountDTO(10L, 1L)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        long id = om.readValue(contentAsString, BankAccount.class).getId();

        mockMvc.perform(get("/api/bank_account/" + id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(10));
    }

    @Test
    void incrBalanceTestOK() throws Exception {
        long userId = userService.save(new UserDTO("firName", "lasName", "0002"));
        long bankId = bankAccountService.save(new BankAccountDTO(0, userId));
        long old = bankAccountService.get(bankId).getBalance();
        long inc = 100;

        mockMvc.perform(patch("/api/bank_account/update/" + bankId)
                        .content(om.writeValueAsString(new UpdateBalanceDTO(inc)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assert (old + inc == bankAccountService.get(bankId).getBalance());
    }
}
