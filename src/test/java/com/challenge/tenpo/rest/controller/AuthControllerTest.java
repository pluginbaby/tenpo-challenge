package com.challenge.tenpo.rest.controller;

import com.challenge.tenpo.rest.dto.LoginDTO;
import com.challenge.tenpo.rest.dto.RegisterDTO;
import com.challenge.tenpo.rest.service.HistoryEndpointService;
import com.challenge.tenpo.rest.service.UserService;
import com.challenge.tenpo.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@WebAppConfiguration
public class AuthControllerTest {

    private static final String BASE_PATH = "/api/auth/";
    private static final String TOKEN = "token";

    private MockMvc mockMvc;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private UserService userService;
    @MockBean
    private JWTUtils jwtUtil;
    @MockBean
    private HistoryEndpointService historyEndpointService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testSignInSuccessfully() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsernameOrEmail("alex");
        loginDTO.setPassword("1234");
        when(jwtUtil.generateJWT(any())).thenReturn(TOKEN);
        final MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH + "signin")
                .content(OBJECT_MAPPER.writeValueAsString(loginDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Authorization", TOKEN))
                .andReturn();

        assertTrue(response.getResponse().getContentAsString().contains("Signin has been done succesfully"));
    }

    @Test
    public void testSignInNoPassword() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsernameOrEmail("alex");
        when(jwtUtil.generateJWT(any())).thenReturn(TOKEN);
        final MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH + "signin")
                .content(OBJECT_MAPPER.writeValueAsString(loginDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertFalse(response.getResponse().getContentAsString().contains("Signin has been done succesfully"));
    }

    @Test
    public void testSignInWrongUrl() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPassword("1234");
        when(jwtUtil.generateJWT(any())).thenReturn(TOKEN);
        final MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH + "signin1234")
                .content(OBJECT_MAPPER.writeValueAsString(loginDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertFalse(response.getResponse().getContentAsString().contains("Signin has been done succesfully"));
    }

    @Test
    public void testSignUpSuccessfully() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setPassword("1234");
        registerDTO.setUsername("alex");
        registerDTO.setEmail("alexrl_lp@hotmail.com");
        registerDTO.setName("alex");
        final MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH + "signup")
                .content(OBJECT_MAPPER.writeValueAsString(registerDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(response.getResponse().getContentAsString().contains("User registered successfully"));
    }

    @Test
    public void testSignUpMissingInformation() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("alex");
        registerDTO.setName("alex");
        final MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH + "signup")
                .content(OBJECT_MAPPER.writeValueAsString(registerDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertFalse(response.getResponse().getContentAsString().contains("User registered successfully"));
    }
}
