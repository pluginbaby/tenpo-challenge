package com.challenge.tenpo.rest.controller;

import com.challenge.tenpo.rest.exceptions.dto.PageDTO;
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

    @GetMapping
    public PageDTO getHistoryEndpoints (FilterDTO filterDTO) {
        PageDTO endpointDTOs = historyEndpointService.getHistoryEndpointsWithPagination(filterDTO);
        return endpointDTOs;
    }

}
