package com.challenge.tenpo.rest.service;


import com.challenge.tenpo.rest.dto.RegisterDTO;
import com.challenge.tenpo.rest.entities.User;
import com.challenge.tenpo.rest.exceptions.EntityAlreadyExistException;
import com.challenge.tenpo.rest.mapper.UserMapper;
import com.challenge.tenpo.rest.repository.IUserRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    @Spy
    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    public void registerUser() {
        RegisterDTO registerDTO = EASY_RANDOM.nextObject(RegisterDTO.class);
        User user = EASY_RANDOM.nextObject(User.class);
        when(userRepository.findByUsernameOrEmail(registerDTO.getUsername(), registerDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toModel(registerDTO)).thenReturn(user);

        userService.registerUser(registerDTO);

        verify(userRepository).save(user);

    }

    @Test
    public void registerAlreadyExistUser() {
        RegisterDTO registerDTO = EASY_RANDOM.nextObject(RegisterDTO.class);
        User user = EASY_RANDOM.nextObject(User.class);
        when(userRepository.findByUsernameOrEmail(registerDTO.getUsername(), registerDTO.getEmail())).thenReturn(Optional.of(user));

        EntityAlreadyExistException exception = assertThrows(EntityAlreadyExistException.class, () -> {
            userService.registerUser(registerDTO);
        });

        Assertions.assertTrue(exception.getMessage().contains("User already exists with"));
        verify(userRepository, never()).save(user);

    }
}
