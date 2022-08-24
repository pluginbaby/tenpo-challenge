package com.challenge.tenpo.rest.client;

import com.challenge.tenpo.rest.dto.PercentageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.QueueDispatcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PercentageClientTest {

    private MockWebServer mockWebServer;
    @Autowired
    private PercentageClient percentageClient;

    @BeforeEach
    private void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(4000);
    }

    @AfterEach
    private void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void getPercentageSuccess () throws JsonProcessingException {
        Double value = Double.valueOf("10");
        PercentageDTO mockResponse = new PercentageDTO(value);
        mockWebServer.setDispatcher(new QueueDispatcher());
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setBody(new ObjectMapper().writeValueAsString(mockResponse))
                        .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        );
        PercentageDTO result = percentageClient.getPercentage();
        Assertions.assertEquals(mockResponse.getPercentage().doubleValue(), result.getPercentage().doubleValue());
    }

    @Test
    public void getPercentageFailed () throws JsonProcessingException {
        Double value = Double.valueOf("10");
        PercentageDTO mockResponse = new PercentageDTO(value);
        mockWebServer.setDispatcher(new QueueDispatcher());
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(500)
                        .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        );
        Exception exception = assertThrows(Exception.class, () -> {
            percentageClient.getPercentage();
        });
        Assertions.assertTrue(exception.getMessage().contains("Percentage Service failed, after max retries of"));
    }

}
