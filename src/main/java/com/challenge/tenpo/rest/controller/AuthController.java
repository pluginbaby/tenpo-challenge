package com.challenge.tenpo.rest.controller;

import com.challenge.tenpo.rest.dto.AuthResponseDTO;
import com.challenge.tenpo.rest.dto.LoginDTO;
import com.challenge.tenpo.rest.dto.RegisterDTO;
import com.challenge.tenpo.rest.service.UserService;
import com.challenge.tenpo.utils.JWTUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JWTUtils jwtUtils;

    @Operation(
            method = "POST",
            summary = "Signing an user",
            description = "Endpoint for authenticate an user",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Bad Credentials"),

            }
    )
    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@Valid @RequestBody LoginDTO loginDto) {
        log.info("Enter request to login a user with body: [{}]", loginDto);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        String token = jwtUtils.generateJWT(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token).body(new AuthResponseDTO("Signin has been done succesfully !"));
    }

    @Operation(
            method = "POST",
            summary = "Register a new user ",
            description = "Endpoint for register an user",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "409", description = "User already created with that username or password"),

            }
    )
    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        log.info("Enter request to register a new user with body: [{}]", registerDTO);
        this.userService.registerUser(registerDTO);
        return new ResponseEntity(new AuthResponseDTO("User registered successfully"), HttpStatus.CREATED);

    }

}
