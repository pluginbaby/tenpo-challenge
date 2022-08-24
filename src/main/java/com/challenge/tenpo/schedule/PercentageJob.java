package com.challenge.tenpo.schedule;

import com.challenge.tenpo.rest.client.PercentageClient;
import com.challenge.tenpo.rest.dto.PercentageDTO;
import com.challenge.tenpo.rest.repository.ISumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@EnableScheduling
@AllArgsConstructor
@Slf4j
@Component
public class PercentageJob {


    private ISumRepository sumRepository;
    private PercentageClient percentageClient;


    @Scheduled(initialDelayString = "${job.initial.delay}", fixedRateString = "${job.fixed.rate}")
    public void getPercentage() {
        try {
            log.info("Starting job at [{}]", LocalDateTime.now());
            PercentageDTO percentageDTO = percentageClient.getPercentage();
            Optional.ofNullable(percentageDTO).ifPresent(result -> sumRepository.setPercentage(result.getPercentage()));
            log.info("Job finalized at [{}]", LocalDateTime.now());
        } catch (Exception e) {
            log.error("Error executing job at [{}]", LocalDateTime.now(), e);
        }

    }

}
