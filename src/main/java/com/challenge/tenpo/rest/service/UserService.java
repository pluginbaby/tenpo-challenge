package com.challenge.tenpo.rest.service;

import com.challenge.tenpo.rest.dto.RegisterDTO;
import com.challenge.tenpo.rest.entities.User;
import com.challenge.tenpo.rest.exceptions.EntityAlreadyExistException;
import com.challenge.tenpo.rest.mapper.UserMapper;
import com.challenge.tenpo.rest.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private IUserRepository userRepository;
    private UserMapper userMapper;


    public void registerUser(RegisterDTO registerDTO) {
        checkIfExists(registerDTO);
        User user = userMapper.toModel(registerDTO);
        userRepository.save(user);
    }


    public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByEmailOrUsername(usernameOrEmail, usernameOrEmail);
    }

    private void checkIfExists(RegisterDTO registerDTO) {
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(registerDTO.getUsername(), registerDTO.getEmail());
        if (userOptional.isPresent()) {
            throw new EntityAlreadyExistException("User already exists with that username or email");
        }
    }

}
