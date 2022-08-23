package com.challenge.tenpo.rest.mapper;

import com.challenge.tenpo.rest.dto.HistoryInformationDTO;
import com.challenge.tenpo.rest.entities.HistoryEndpoint;
import com.challenge.tenpo.rest.exceptions.dto.HistoryEndpointDTO;
import org.springframework.stereotype.Component;

@Component
public class HistoryEndpointsMapper {

    public HistoryEndpoint toModel(HistoryInformationDTO dto) {
        HistoryEndpoint historyEndpoint = new HistoryEndpoint();
        historyEndpoint.setEndpointUrl(dto.getEndpointUrl());
        historyEndpoint.setMethod(dto.getMethod());
        historyEndpoint.setStatus(dto.getStatus());
        historyEndpoint.setExecutedTime(dto.getExecutedTime());
        if (dto.getStatus().is2xxSuccessful()) {
            historyEndpoint.setContent(dto.getContent());
        }
        return historyEndpoint;
    }

    public HistoryEndpointDTO toDTO (HistoryEndpoint model) {
        HistoryEndpointDTO dto = new HistoryEndpointDTO();
        dto.setContent(model.getContent());
        dto.setMethod(model.getMethod().name());
        dto.setStatus(model.getStatus().value());
        dto.setEndpointUrl(model.getEndpointUrl());
        dto.setExecutedTime(model.getExecutedTime());
        return dto;
    }


}
