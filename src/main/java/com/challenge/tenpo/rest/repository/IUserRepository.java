package com.challenge.tenpo.repository;

import com.challenge.tenpo.rest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    //Used for signin
    Optional<User> findByEmailOrUsername(String username, String username2);

    //Check in the registration if a user already exists
    Optional<User> findByUsernameOrEmail (String username, String email);

}
