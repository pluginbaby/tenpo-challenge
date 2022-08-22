package com.challenge.tenpo.service;

import com.challenge.tenpo.dto.SumResponseDTO;
import com.challenge.tenpo.repository.ISumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SumService {

    private ISumRepository sumRepository;

    public SumResponseDTO calculateSum(Double number1, Double number2) {
        return sumRepository.getPercentage()
                .map(percentage -> doSum(number1, number2, percentage))
                .orElseThrow(() -> new IllegalStateException("Couldn't calculate the sum since there is no percentage present"));
    }

    private SumResponseDTO doSum(Double number1, Double number2, Double percentage) {
        percentage = percentage/100 + 1;
        return new SumResponseDTO((number1 + number2) * percentage);
    }

}
