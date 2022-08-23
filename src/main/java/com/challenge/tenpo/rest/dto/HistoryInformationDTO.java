package com.challenge.tenpo.rest.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class HistoryInformationDTO {

    private HttpStatus status;
    private HttpMethod method;
    private String content;
    private String endpointUrl;
    private LocalDateTime executedTime;
}
