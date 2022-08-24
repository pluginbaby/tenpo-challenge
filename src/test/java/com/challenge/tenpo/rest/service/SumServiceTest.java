package com.challenge.tenpo.rest.service;

import com.challenge.tenpo.rest.dto.SumResponseDTO;
import com.challenge.tenpo.rest.exceptions.EntityAlreadyExistException;
import com.challenge.tenpo.rest.repository.ISumRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SumServiceTest {

    @Spy
    @InjectMocks
    private SumService sumService;

    @Mock
    private ISumRepository sumRepository;

    @Test
    public void calculateSum() {
        when(sumRepository.getPercentage()).thenReturn(Optional.of(Double.valueOf("50")));

        SumResponseDTO dto = sumService.calculateSum(Double.valueOf("100"), Double.valueOf("100"));
        Assertions.assertEquals(Double.valueOf("300"), dto.getResult());

    }

    @Test
    public void calculateSumNotPercentagePresent() {
        when(sumRepository.getPercentage()).thenReturn(Optional.empty());

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            sumService.calculateSum(Double.valueOf("3"), Double.valueOf("4"));
        });

        Assertions.assertTrue(exception.getMessage().contains("Couldn't calculate the sum"));

    }


}

