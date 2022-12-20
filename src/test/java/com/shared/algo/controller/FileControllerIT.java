package com.shared.algo.controller;

import com.shared.algo.utils.GenericResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileControllerIT {

    private static final Logger LOG = LoggerFactory.getLogger(FileControllerIT.class);

    @LocalServerPort
    String port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void getContentIT() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        headers.setBasicAuth("suhas", "suhas");
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(buildUrl("/file/fetch-content"));
        builder.queryParam("fileName", "PDFContent.txt");

        ResponseEntity<GenericResponse> response =
                restTemplate.exchange(builder.toUriString(),
                        HttpMethod.GET, httpEntity, GenericResponse.class);

        LOG.info("{}", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    private String buildUrl(String contextPath) {
        String url = "http://localhost:" + port + contextPath;
        LOG.info("URL : {}", url);
        return url;
    }
}
