package com.challenge.tenpo.rest.repository;

import com.challenge.tenpo.rest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    //Used for signin
    @Query("SELECT u from User u WHERE u.username=?1 OR u.email=?1 ")
    Optional<User> findByEmailOrUsername(String usernameOrEmail);

    //Check during the registration if an user already exists
    Optional<User> findByUsernameOrEmail(String username, String email);

}
