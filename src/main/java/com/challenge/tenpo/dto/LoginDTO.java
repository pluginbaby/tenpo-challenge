package com.challenge.tenpo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {

    @NotNull
    private String usernameOrEmail;
    @NotNull
    private String password;

}
