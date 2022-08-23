package com.challenge.tenpo.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {

    @NotNull
    private String usernameOrEmail;
    @NotNull
    private String password;

}
