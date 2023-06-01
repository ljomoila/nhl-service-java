package com.ljomoila.nhl.integration;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class NhlClient {
    private static final Logger logger = LoggerFactory.getLogger(NhlClient.class);

    // TODO: move to properties
    private static final String API_BASE_URL = "https://statsapi.web.nhl.com";
    public static final String API_PATH = "/api/v1";

    private final RestTemplate restTemplate;

    public NhlClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String get(String path) {
        try {
            ResponseEntity<String> response = this.restTemplate.getForEntity(API_BASE_URL + path, String.class);

            if (response.getStatusCodeValue() != HttpStatus.SC_OK) {
                throw new Exception("Get failed: path " + path + ", status: " + response.getStatusCodeValue());
            }

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
