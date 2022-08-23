package com.challenge.tenpo.rest.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryEndpointDTO {

    private Integer status;
    private String method;
    private String content;
    private String endpointUrl;
    private LocalDateTime executedTime;
}
