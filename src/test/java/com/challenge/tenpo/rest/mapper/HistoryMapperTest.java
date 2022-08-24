package com.challenge.tenpo.rest.mapper;

import com.challenge.tenpo.rest.dto.HistoryInformationDTO;
import com.challenge.tenpo.rest.entities.HistoryEndpoint;
import com.challenge.tenpo.rest.exceptions.dto.HistoryEndpointDTO;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class HistoryMapperTest {

    private HistoryEndpointsMapper historyEndpointsMapper = new HistoryEndpointsMapper();
    private final static EasyRandom EASY_RANDOM = new EasyRandom();

    @Test
    public void toModelWith2xxStatus () {
        HistoryInformationDTO dto = EASY_RANDOM.nextObject(HistoryInformationDTO.class);
        dto.setStatus(HttpStatus.CREATED);
        HistoryEndpoint historyEndpoint = historyEndpointsMapper.toModel(dto);
        Assertions.assertEquals(dto.getStatus(), historyEndpoint.getStatus());
        Assertions.assertEquals(dto.getContent(), historyEndpoint.getContent());
        Assertions.assertEquals(dto.getEndpointUrl(), historyEndpoint.getEndpointUrl());
        Assertions.assertEquals(dto.getExecutedTime(), historyEndpoint.getExecutedTime());
        Assertions.assertEquals(dto.getMethod(), historyEndpoint.getMethod());

    }

    @Test
    public void toModelWithNot2xxStatus () {
        HistoryInformationDTO dto = EASY_RANDOM.nextObject(HistoryInformationDTO.class);
        dto.setStatus(HttpStatus.BAD_REQUEST);
        HistoryEndpoint historyEndpoint = historyEndpointsMapper.toModel(dto);
        Assertions.assertEquals(dto.getStatus(), historyEndpoint.getStatus());
        Assertions.assertEquals(null, historyEndpoint.getContent());
        Assertions.assertEquals(dto.getEndpointUrl(), historyEndpoint.getEndpointUrl());
        Assertions.assertEquals(dto.getExecutedTime(), historyEndpoint.getExecutedTime());
        Assertions.assertEquals(dto.getMethod(), historyEndpoint.getMethod());

    }

    @Test
    public void toDTO () {
        HistoryEndpoint historyEndpoint = EASY_RANDOM.nextObject(HistoryEndpoint.class);
        HistoryEndpointDTO dto = historyEndpointsMapper.toDTO(historyEndpoint);
        Assertions.assertEquals(historyEndpoint.getStatus().value(), dto.getStatus());
        Assertions.assertEquals(historyEndpoint.getContent(), dto.getContent());
        Assertions.assertEquals(historyEndpoint.getEndpointUrl(), dto.getEndpointUrl());
        Assertions.assertEquals(historyEndpoint.getExecutedTime(), dto.getExecutedTime());
        Assertions.assertEquals(historyEndpoint.getMethod().name(), dto.getMethod());

    }


}
