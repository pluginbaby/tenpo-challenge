package com.challenge.tenpo.rest.controller;

import com.challenge.tenpo.filter.HistoryEndpointsFilter;
import com.challenge.tenpo.rest.dto.SumResponseDTO;
import com.challenge.tenpo.rest.service.HistoryEndpointService;
import com.challenge.tenpo.rest.service.SumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SumController.class)
@WebAppConfiguration
public class SumControllerTest {

    private static final String BASE_URL = "/api/sum";

    private MockMvc mockMvc;
    @MockBean
    private SumService sumService;
    @MockBean
    private HistoryEndpointService historyEndpointService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void sumTestSuccess() throws Exception {
        SumResponseDTO sumResponseDTO = new SumResponseDTO(Double.valueOf("321"));
        when(sumService.calculateSum(Double.valueOf("12"), Double.valueOf("45"))).thenReturn(sumResponseDTO);
        final MvcResult response = this.mockMvc.perform(get(BASE_URL + "?number1=12&number2=45")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(isNotBlank(response.getResponse().getContentAsString()));

    }

    @Test
    public void sumTestBadRequest() throws Exception {
        SumResponseDTO sumResponseDTO = new SumResponseDTO(Double.valueOf("321"));
        when(sumService.calculateSum(Double.valueOf("12"), Double.valueOf("45"))).thenReturn(sumResponseDTO);
        final MvcResult response = this.mockMvc.perform(get(BASE_URL + "?number1=12")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

    }
}
