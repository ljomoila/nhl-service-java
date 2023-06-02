package com.ljomoila.nhl.integration;

import com.ljomoila.nhl.exception.NhlException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class NhlClient {
    private final RestTemplate restTemplate;

    @Value( "${nhl.api.url}" )
    private String apiBaseUrl;

    @Value( "${nhl.api.path}" )
    private String apiPath;

    public NhlClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String get(String path) {
        try {
            ResponseEntity<String> response = this.restTemplate.getForEntity(constructUrlWithPath(path), String.class);

            if (response.getStatusCodeValue() != HttpStatus.SC_OK) {
                throw new NhlException("Invalid status", org.springframework.http.HttpStatus.valueOf(response.getStatusCodeValue()));
            }

            return response.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    private String constructUrlWithPath(String path) {
        if (StringUtils.contains(path, this.apiPath))
            return this.apiBaseUrl + path;

        return this.apiBaseUrl + this.apiPath + path;
    }
}
