package com.example.bankapi;


import com.example.bankapi.DTO.BankAccount_DTO;
import com.example.bankapi.DTO.CardDTO;
import com.example.bankapi.DTO.UserDTO;
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
    void addCard_OK() throws Exception {
        long user_id = userService.save(new UserDTO("firName", "lasName", "0001"));
        long bank_id = bankAccountService.save(new BankAccount_DTO(0, user_id));

        mockMvc.perform(post("/api/card/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(new CardDTO("3001 0000 0000 0000", bank_id))))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/card/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(new CardDTO("3001 0000 0000 0000", bank_id   ))))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void addCardWithNoExistsFK_4xx() throws Exception {
        mockMvc.perform(post("/api/card/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(new CardDTO("3000 0000 0000 0000", -1L))))
                .andExpect(status().is4xxClientError());
    }
}
