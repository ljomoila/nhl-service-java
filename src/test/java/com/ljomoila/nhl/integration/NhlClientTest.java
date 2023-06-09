package com.ljomoila.nhl.integration;

import com.ljomoila.nhl.exception.NhlException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NhlClientTest {
    @Mock
    RestTemplate restTemplate;
    
    NhlClient client;

    @Before
    public void setup() {
        client = new NhlClient(restTemplate, "http://localhost", "/api/v1");
    }

    @Test
    public void testGetWithOkResponse() {
        // given
        when(restTemplate.getForEntity("http://localhost/api/v1/teams", String.class)).thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));

        // when
        String response = client.get("/teams");

        // then
        assertEquals("OK", response);
    }

    @Test
    public void testGetWithOkResponseAndApiPath() {
        // given
        when(restTemplate.getForEntity("http://localhost/api/v1/teams", String.class)).thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));

        // when
        String response = client.get("/api/v1/teams");

        // then
        assertEquals("OK", response);
    }


    @Test
    public void testGetWithInvalidResponseThrows() {
        // given
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity<>("FORBIDDEN", HttpStatus.FORBIDDEN));

        try {
            // when
            client.get("/teams");

            assertEquals(false, true);
        } catch(NhlException e) {
            // then
            assertEquals(e.getStatus(), HttpStatus.FORBIDDEN);
        }
    }
}
