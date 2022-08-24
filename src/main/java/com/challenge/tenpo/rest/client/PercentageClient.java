package com.challenge.tenpo.rest.client;

import com.challenge.tenpo.rest.dto.PercentageDTO;
import com.challenge.tenpo.rest.exceptions.ExceedMaxAttemptsException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class PercentageClient {

    private final WebClient.Builder webClient;

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
                .bodyToMono(PercentageDTO.class)
                .retryWhen(Retry.backoff(maxRetry, Duration.ofSeconds(durationRequest))
                        .filter(this::is5xxServerError)
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> new ExceedMaxAttemptsException(
                                "Percentage Service failed, after max retries of: "
                                        + retrySignal.totalRetries()) {
                        }))
                .block();
    }

    private boolean is5xxServerError(Throwable throwable) {
        return throwable instanceof WebClientResponseException &&
                ((WebClientResponseException) throwable).getStatusCode().is5xxServerError();
    }

}
