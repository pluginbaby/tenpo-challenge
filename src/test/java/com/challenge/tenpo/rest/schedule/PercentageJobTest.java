package com.challenge.tenpo.rest.schedule;

import com.challenge.tenpo.rest.client.PercentageClient;
import com.challenge.tenpo.rest.dto.PercentageDTO;
import com.challenge.tenpo.rest.exceptions.ExceedMaxAttemptsException;
import com.challenge.tenpo.rest.repository.ISumRepository;
import com.challenge.tenpo.schedule.PercentageJob;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PercentageJobTest {

    @InjectMocks
    @Spy
    private PercentageJob percentageJob;

    @Mock
    private ISumRepository sumRepository;
    @Mock
    private PercentageClient percentageClient;

    @Test
    public void getPercentageTestSuccess (){
        PercentageDTO dto = new PercentageDTO(Double.valueOf("10"));
        when(percentageClient.getPercentage()).thenReturn(dto);
        percentageJob.getPercentage();
        Mockito.verify(sumRepository).setPercentage(dto.getPercentage());
    }

    @Test
    public void getPercentageTestFail (){
        PercentageDTO dto = new PercentageDTO(Double.valueOf("10"));
        when(percentageClient.getPercentage()).thenThrow(new RuntimeException("Percentage Service failed, after max retries of"));
        percentageJob.getPercentage();
        Mockito.verify(sumRepository, never()).setPercentage(dto.getPercentage());
    }

    @Test
    public void getPercentageTestFailWithNull (){
        PercentageDTO dto = new PercentageDTO(Double.valueOf("10"));
        when(percentageClient.getPercentage()).thenReturn(null);
        percentageJob.getPercentage();
        Mockito.verify(sumRepository, never()).setPercentage(dto.getPercentage());
    }

}
