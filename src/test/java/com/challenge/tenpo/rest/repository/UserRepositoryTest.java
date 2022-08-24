package com.challenge.tenpo.rest.repository;

import com.challenge.tenpo.rest.entities.User;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void findByEmailOrUsernameSuccess () {
        User user = EASY_RANDOM.nextObject(User.class);
        userRepository.save(user);
        Assertions.assertTrue(userRepository.findByEmailOrUsername("test").isEmpty());
        Assertions.assertNotNull(userRepository.findByEmailOrUsername(user.getUsername()));
        Assertions.assertNotNull(userRepository.findByEmailOrUsername(user.getEmail()));
    }

    @Test
    public void findByUsernameOrEmailSuccess () {
        User user = EASY_RANDOM.nextObject(User.class);
        userRepository.save(user);
        Assertions.assertTrue(userRepository.findByUsernameOrEmail("test", "test").isEmpty());
        Assertions.assertNotNull(userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()));
        Assertions.assertNotNull(userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()));
    }
}
