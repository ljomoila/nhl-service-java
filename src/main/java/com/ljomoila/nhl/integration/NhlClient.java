package com.ljomoila.nhl.integration;

import com.ljomoila.nhl.exception.NhlException;
import org.apache.commons.lang3.StringUtils;
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
    private static final String API_PATH = "/api/v1";

    private final RestTemplate restTemplate;

    public NhlClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String get(String path) {
        try {
            String url = constructUrlWithPath(path);
            ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);

            if (response.getStatusCodeValue() != HttpStatus.SC_OK) {
                throw new Exception("Invalid response for get: path: " + path + ", status: " + response.getStatusCodeValue());
            }

            return response.getBody();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NhlException(e);
        }
    }

    private static String constructUrlWithPath(String path) {
        if (StringUtils.contains(path, API_PATH))
            return API_BASE_URL + path;

        return API_BASE_URL + API_PATH + path;
    }
}
