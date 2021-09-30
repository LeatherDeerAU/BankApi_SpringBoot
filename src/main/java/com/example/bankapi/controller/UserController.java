package com.example.bankapi.controller;

import com.example.bankapi.dto.UserDTO;
import com.example.bankapi.model.User;
import com.example.bankapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{number}")
    User getUser(@PathVariable String number) {
        return userService.getByNumber(number);
    }

    @PostMapping("/new")
    Map save(@RequestBody UserDTO userDTO) {
        return Collections.singletonMap("id", userService.save(userDTO));
    }

    @GetMapping("/all")
    List<User> getAll() {
        return userService.getAll();
    }
}
