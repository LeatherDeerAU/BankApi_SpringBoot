package com.example.bankapi;


import com.example.bankapi.dto.BankAccountDTO;
import com.example.bankapi.dto.CardDTO;
import com.example.bankapi.dto.UserDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {
    private final static ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    UserService userService;


    @Test
    void getNoExistCard() throws Exception {
        mockMvc.perform(get("/api/card/0000 0000 0000"))
                .andExpect(status().is4xxClientError());
    }


    @Test
    void addCardOK() throws Exception {
        long userId = userService.save(new UserDTO("firName", "lasName", "0001"));
        long bankId = bankAccountService.save(new BankAccountDTO(0, userId));

        mockMvc.perform(post("/api/card/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(new CardDTO("3001 0000 0000 0000", bankId))))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/card/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(new CardDTO("3001 0000 0000 0000", bankId))))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void addCardWithNoExistsFK4xx() throws Exception {
        mockMvc.perform(post("/api/card/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(new CardDTO("3000 0000 0000 0000", -1L))))
                .andExpect(status().is4xxClientError());
    }
}
