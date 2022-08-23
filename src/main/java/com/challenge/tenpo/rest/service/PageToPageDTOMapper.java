package com.challenge.tenpo.rest.service;

import com.challenge.tenpo.rest.exceptions.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageToPageDTOMapper {

    public PageDTO pageToPageDTO(Page<?> page) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setContent(page.getContent());
        pageDTO.setTotalItems(page.getTotalElements());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setCurrentPage(page.getNumber());
        pageDTO.setSize(page.getSize());
        return pageDTO;
    }
}
