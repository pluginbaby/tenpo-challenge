package com.challenge.tenpo.rest.controller;

import com.challenge.tenpo.rest.dto.FilterDTO;
import com.challenge.tenpo.rest.exceptions.dto.PageDTO;
import com.challenge.tenpo.rest.service.HistoryEndpointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/histories")
@AllArgsConstructor
public class HistoryEndpointController {

    private HistoryEndpointService historyEndpointService;

    @Operation(
            method = "Get",
            summary = "Get history of all endpoints ",
            description = "Endpoint that return the history of all endpoints executed",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized. Access token is not valid or not present."),

            }
    )
    @GetMapping
    public PageDTO getHistoryEndpoints (FilterDTO filterDTO) {
        log.info("Enter request to get history endpoints with search: [{}]", filterDTO);
        PageDTO endpointDTOs = historyEndpointService.getHistoryEndpointsWithPagination(filterDTO);
        return endpointDTOs;
    }

}
