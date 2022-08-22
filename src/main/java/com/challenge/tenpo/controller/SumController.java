package com.challenge.tenpo.controller;

import com.challenge.tenpo.dto.SumResponseDTO;
import com.challenge.tenpo.service.SumService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public SumResponseDTO calculateSum(@NotNull @RequestParam("number1") Double number1,
                                       @NotNull @RequestParam("number2") Double number2) {

        SumResponseDTO response = sumService.calculateSum(number1, number2);
        return response;
    }

}
