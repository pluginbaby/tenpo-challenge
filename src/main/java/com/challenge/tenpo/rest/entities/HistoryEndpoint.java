package com.challenge.tenpo.rest.entities;

import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "history_endpoints")
public class HistoryEndpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HttpStatus status;
    @Enumerated(EnumType.STRING)
    private HttpMethod method;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(name = "endpoint_url")
    private String endpointUrl;
    @Column(name = "executed_time")
    private LocalDateTime executedTime;

}
