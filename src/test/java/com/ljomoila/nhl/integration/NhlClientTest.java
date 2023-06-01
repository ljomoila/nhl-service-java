package com.ljomoila.nhl.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NhlClientTest {
    @Mock
    RestTemplate restTemplate;

    NhlClient client;

    @Before
    public void setup() {
        client = new NhlClient(restTemplate);
    }

    @Test
    public void testGet() {
        // given
        //HttpResponse<String> expectedReponse = new Http
        //when(client.get("test")).thenReturn()

        // when

        // then
    }

}
