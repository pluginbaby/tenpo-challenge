package com.challenge.tenpo.rest.controller;

import com.challenge.tenpo.rest.dto.FilterDTO;
import com.challenge.tenpo.rest.dto.SumResponseDTO;
import com.challenge.tenpo.rest.exceptions.dto.PageDTO;
import com.challenge.tenpo.rest.service.HistoryEndpointService;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HistoryEndpointController.class)
@WebAppConfiguration
public class HistoryEndpointControllerTest {

    private static final String BASE_URL = "/api/histories";

    private MockMvc mockMvc;
    @MockBean
    private HistoryEndpointService historyEndpointService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getHistoryEndpointsSuccess() throws Exception {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCurrentPage(1);
        pageDTO.setSize(3);
        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setSize(1);
        filterDTO.setPage(3);
        when(historyEndpointService.getHistoryEndpointsWithPagination(filterDTO)).thenReturn(pageDTO);
        final MvcResult response = this.mockMvc.perform(get(BASE_URL + "?size=1&page=3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(isNotBlank(response.getResponse().getContentAsString()));

    }
}
