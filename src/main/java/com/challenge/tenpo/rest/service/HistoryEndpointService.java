package com.challenge.tenpo.rest.service;

import com.challenge.tenpo.rest.exceptions.dto.PageDTO;
import com.challenge.tenpo.repository.IHistoryEndpointsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoryEndpointService {

    private IHistoryEndpointsRepository historyEndpointsRepository;
    private HistoryEndpointsMapper historyEndpointsMapper;
    private PageToPageDTOMapper pageToPageDTOMapper;

    @Async
    public HistoryEndpoint saveHistory(HistoryInformationDTO dto) {
        return historyEndpointsRepository.save(this.historyEndpointsMapper.toModel(dto));
    }

    public PageDTO getHistoryEndpointsWithPagination(FilterDTO filterDTO) {
        return pageToPageDTOMapper.pageToPageDTO(
                historyEndpointsRepository.findAll(PageRequest.of(filterDTO.getPage(), filterDTO.getSize()))
                        .map(historyEndpoint -> historyEndpointsMapper.toDTO(historyEndpoint))
        );
    }


}
