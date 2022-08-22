package com.challenge.tenpo.schedule;

import com.challenge.tenpo.client.PercentageClient;
import com.challenge.tenpo.dto.PercentageDTO;
import com.challenge.tenpo.repository.ISumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Optional;

@EnableScheduling
@AllArgsConstructor
@Slf4j
public class PercentageJob {


    private ISumRepository sumRepository;
    private PercentageClient percentageClient;

    @Scheduled(cron = "${tenpo.service.job.cron: 0 */30 * * * *}")
    private void getPercentage() {
        try {
            log.info("Starting job at [{}]", LocalDateTime.now());
            PercentageDTO percentageDTO = percentageClient.getPercentage();
            Optional.ofNullable(percentageDTO).ifPresent(result -> sumRepository.setPercentage(result.getPercentage()));
        } catch (Exception e) {
            log.error("Error executing job at [{}]", LocalDateTime.now());
        }


    }

}