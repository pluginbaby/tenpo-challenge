package com.challenge.tenpo.rest.exceptions.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO {

    private Long totalItems;
    private Integer currentPage;
    private Integer totalPages;
    private Integer size;
    private List<?> content;

}
