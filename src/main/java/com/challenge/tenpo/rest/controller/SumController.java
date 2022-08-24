package com.challenge.tenpo.rest.controller;

import com.challenge.tenpo.rest.dto.SumResponseDTO;
import com.challenge.tenpo.rest.service.SumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@RequestMapping("/api/sum")
@AllArgsConstructor
public class SumController {

    private SumService sumService;

    @Operation(
            method = "Get",
            summary = "Get sum of two numbers ",
            description = "Return the result of summing two numbers plus the percentage obtained for third service",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized. Access token is not valid or not present."),
                    @ApiResponse(responseCode = "500", description = "Couldn't calculate the sum since there is no percentage present"),

            }
    )
    @GetMapping
    public SumResponseDTO calculateSum(@NotNull @RequestParam("number1") Double number1,
                                       @NotNull @RequestParam("number2") Double number2) {

        log.info("Enter request to calculate sum between number1: [{}] and number2: [{}]", number1,number2);
        SumResponseDTO response = sumService.calculateSum(number1, number2);
        return response;
    }

}
