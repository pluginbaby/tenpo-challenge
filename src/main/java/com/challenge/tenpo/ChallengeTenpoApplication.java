package com.challenge.tenpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ChallengeTenpoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeTenpoApplication.class, args);
    }

}
