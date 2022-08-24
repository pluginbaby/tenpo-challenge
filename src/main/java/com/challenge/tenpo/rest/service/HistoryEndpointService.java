package com.challenge.tenpo.rest.service;

import com.challenge.tenpo.rest.dto.FilterDTO;
import com.challenge.tenpo.rest.dto.HistoryInformationDTO;
import com.challenge.tenpo.rest.entities.HistoryEndpoint;
import com.challenge.tenpo.rest.exceptions.dto.PageDTO;
import com.challenge.tenpo.rest.mapper.HistoryEndpointsMapper;
import com.challenge.tenpo.rest.mapper.PageMapper;
import com.challenge.tenpo.rest.repository.IHistoryEndpointsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoryEndpointService {

    private IHistoryEndpointsRepository historyEndpointsRepository;
    private HistoryEndpointsMapper historyEndpointsMapper;
    private PageMapper pageMapper;

    @Async
    public HistoryEndpoint saveHistory(HistoryInformationDTO dto) {
        return historyEndpointsRepository.save(this.historyEndpointsMapper.toModel(dto));
    }

    public PageDTO getHistoryEndpointsWithPagination(FilterDTO filterDTO) {
        return pageMapper.toDTO(
                historyEndpointsRepository.findAll(PageRequest.of(filterDTO.getPage(), filterDTO.getSize()))
                        .map(historyEndpoint -> historyEndpointsMapper.toDTO(historyEndpoint)));
    }


}
