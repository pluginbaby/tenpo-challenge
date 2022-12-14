package com.challenge.tenpo.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;

}
