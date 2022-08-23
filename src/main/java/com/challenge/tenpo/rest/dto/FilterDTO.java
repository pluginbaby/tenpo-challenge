package com.challenge.tenpo.rest.dto;

import lombok.Data;

@Data
public class FilterDTO {

    private static final Integer DEFAULT_PAGE_SIZE = 5;
    private static final Integer DEFAULT_PAGE = 0;

    private Integer size = DEFAULT_PAGE_SIZE;
    private Integer page = DEFAULT_PAGE;

}
