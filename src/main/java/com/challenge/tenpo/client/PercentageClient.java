package com.challenge.tenpo.client;

import com.challenge.tenpo.dto.PercentageDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class PercentageClient {

    @Autowired
    private WebClient.Builder webClient;

    @Value("${sum_client_base_url}")
    private String basePath;
    @Value("${sum_client_sum_path}")
    private String percentagePath;
    @Value("${sum_client_max_retry}")
    private Integer maxRetry;
    @Value("${sum_client_duration_request}")
    private Integer durationRequest;


    public PercentageDTO getPercentage () {
        return webClient.build()
                .get()
                .uri(basePath + percentagePath)
                .retrieve()
                .bodyToFlux(PercentageDTO.class)
                .retryWhen(Retry.backoff(maxRetry, Duration.ofMillis(durationRequest))
                        .filter(throwable -> throwable instanceof WebClientRequestException)
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> retrySignal.failure()))
                .blockFirst();
    }

}
