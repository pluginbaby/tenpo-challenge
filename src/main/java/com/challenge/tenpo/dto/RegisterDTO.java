package com.challenge.tenpo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDTO {

    @NotNull
    private String name;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;

}
