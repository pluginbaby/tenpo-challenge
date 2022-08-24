package com.challenge.tenpo.rest.mapper;

import com.challenge.tenpo.rest.exceptions.dto.HistoryEndpointDTO;
import com.challenge.tenpo.rest.exceptions.dto.PageDTO;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class PageMapperTest {

    private final static EasyRandom EASY_RANDOM = new EasyRandom();
    private PageMapper pageMapper = new PageMapper();

    @Test
    public void toDTO () {
        HistoryEndpointDTO dto = EASY_RANDOM.nextObject(HistoryEndpointDTO.class);
        HistoryEndpointDTO dto2 = EASY_RANDOM.nextObject(HistoryEndpointDTO.class);
        HistoryEndpointDTO dto3 = EASY_RANDOM.nextObject(HistoryEndpointDTO.class);
        HistoryEndpointDTO dto4 = EASY_RANDOM.nextObject(HistoryEndpointDTO.class);
        PageDTO result = pageMapper.toDTO(new PageImpl<>(List.of(dto, dto2,dto3,dto4), PageRequest.of(1,2), 4));
        Assertions.assertEquals(4, result.getTotalItems());
        Assertions.assertEquals(1, result.getCurrentPage());
        Assertions.assertEquals(2, result.getTotalPages());
        Assertions.assertEquals(2, result.getSize());
        Assertions.assertEquals(4, result.getContent().size());

    }


}
