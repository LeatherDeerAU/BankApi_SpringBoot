package com.example.bankapi;


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
public class UserControllerTest {
    private final static ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    UserService userService;

    @Test
    void findNoExistUser_4xx() throws Exception {
        mockMvc.perform(get("/api/user/0"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findUser_OK() throws Exception {
        userService.save(new UserDTO("firName", "lasName", "0001"));
        mockMvc.perform(get("/api/user/0001"))
                .andExpect(status().isOk());
    }

    @Test
    void saveUser_ok() throws Exception {

        mockMvc.perform(post("/api/user/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(new UserDTO("firName", "lasName", "0001"))))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/user/0001"))
                .andExpect(status().isOk());
    }
}
