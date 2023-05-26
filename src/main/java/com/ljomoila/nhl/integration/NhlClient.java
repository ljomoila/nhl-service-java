package com.ljomoila.nhl.integration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class NhlClient {

    // TODO: move to properties
    private static final String API_BASE_URL = "https://statsapi.web.nhl.com";
    public static final String API_PATH = "/api/v1";

    public String get(String path) {
        try {
            HttpClient client = java.net.http.HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_BASE_URL + path))
                    .GET()
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Http GET failed with error code: "
                        + response.statusCode());
            } else if (StringUtils.isEmpty(response.body())) {
                throw new RuntimeException("Invalid response");
            }

            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
