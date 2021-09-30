package com.example.bankapi.service;

import com.example.bankapi.dto.UserDTO;
import com.example.bankapi.model.User;
import com.example.bankapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(long id) {
        Optional<User> user = Optional.ofNullable(userRepository.get(id));
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public User getByNumber(String number) {
        Optional<User> user = Optional.ofNullable(userRepository.getByNumber(number));
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public long save(UserDTO userDTO) {
        try {
            return userRepository.save(userDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }
}
