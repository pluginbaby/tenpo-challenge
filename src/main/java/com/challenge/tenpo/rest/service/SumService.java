package com.challenge.tenpo.rest.service;

import com.challenge.tenpo.rest.dto.SumResponseDTO;
import com.challenge.tenpo.rest.repository.ISumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SumService {

    private ISumRepository sumRepository;

    public SumResponseDTO calculateSum(Double number1, Double number2) {
        return sumRepository.getPercentage()
                .map(percentage -> doSum(number1, number2, percentage))
                .orElseThrow(() -> new IllegalStateException("Couldn't calculate the sum since there is no percentage present"));
    }

    private SumResponseDTO doSum(Double number1, Double number2, Double percentage) {
        percentage = percentage / 100 + 1;
        log.info("Calculating the sum between number1: {} , number2: {}. and percentage : {}", number1, number2, percentage);
        return new SumResponseDTO((number1 + number2) * percentage);
    }

}
