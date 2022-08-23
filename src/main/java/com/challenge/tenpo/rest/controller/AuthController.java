package com.challenge.tenpo.rest.controller;

import com.challenge.tenpo.rest.dto.AuthResponseDTO;
import com.challenge.tenpo.rest.dto.LoginDTO;
import com.challenge.tenpo.rest.dto.RegisterDTO;
import com.challenge.tenpo.rest.service.UserService;
import com.challenge.tenpo.utils.JWTUtils;
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


    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@Valid @RequestBody LoginDTO loginDto){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));


        
        String token = jwtUtils.generateJWT(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token).body(new AuthResponseDTO("Signin has been done succesfully !"));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> registerUser(@Valid @RequestBody RegisterDTO registerDTO){
        this.userService.registerUser(registerDTO);
        return new ResponseEntity(new AuthResponseDTO("User registered successfully"), HttpStatus.CREATED);

    }

}
