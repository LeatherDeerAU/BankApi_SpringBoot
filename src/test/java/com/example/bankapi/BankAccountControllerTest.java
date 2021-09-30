package com.example.bankapi;


import com.example.bankapi.DTO.BankAccount_DTO;
import com.example.bankapi.DTO.UpdateBalanceDTO;
import com.example.bankapi.DTO.UserDTO;
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
    void saveBankAccount_OK() throws Exception {
        userService.save(new UserDTO("firName", "lasName", "0001"));
        String contentAsString = mockMvc.perform(post("/api/bank_account/new")
                .content(om.writeValueAsString(new BankAccount_DTO(10L, 1L)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        long id = om.readValue(contentAsString, BankAccount.class).getId();

        mockMvc.perform(get("/api/bank_account/" + id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(10));
    }
    @Test
    void incrBalanceTest_OK() throws Exception {
        long user_id = userService.save(new UserDTO("firName", "lasName", "0001"));
        long id = bankAccountService.save(new BankAccount_DTO(0, user_id));
        long old = bankAccountService.get(id).getBalance();
        long inc = 100;

        mockMvc.perform(patch("/api/bank_account/update/1")
                .content(om.writeValueAsString(new UpdateBalanceDTO(inc)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assert (old + inc == bankAccountService.get(id).getBalance());
    }
}
