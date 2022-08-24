package com.challenge.tenpo.rest.mapper;

import com.challenge.tenpo.rest.dto.RegisterDTO;
import com.challenge.tenpo.rest.entities.User;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    private final static EasyRandom EASY_RANDOM = new EasyRandom();


    @Spy
    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void toModel () {
        RegisterDTO registerDTO = EASY_RANDOM.nextObject(RegisterDTO.class);
        when(passwordEncoder.encode(registerDTO.getPassword())).thenReturn("encoded");
        User user = userMapper.toModel(registerDTO);
        Assertions.assertEquals(registerDTO.getEmail(), user.getEmail());
        Assertions.assertEquals(registerDTO.getUsername(), user.getUsername());
        Assertions.assertEquals(registerDTO.getName(), user.getName());
        Assertions.assertEquals("encoded", user.getPassword());
    }


}
