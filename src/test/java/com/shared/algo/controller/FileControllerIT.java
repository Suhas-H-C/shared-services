package com.shared.algo.controller;

import com.shared.algo.utils.GenericResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileControllerIT {

    private static final Logger LOG = LoggerFactory.getLogger(FileControllerIT.class);

    @LocalServerPort
    String port;

    @Autowired
    private FileController fileController;

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

    @Test
    void getJsonIT() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        headers.setBasicAuth("suhas", "suhas");
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(buildUrl("/file/fetch-json"));
        builder.queryParam("stringPath", "ipData");

        ResponseEntity<GenericResponse> response =
                restTemplate.exchange(builder.toUriString(),
                        HttpMethod.GET, httpEntity, GenericResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void readCsvIT() throws IOException {
        ResponseEntity<GenericResponse<?>> response = fileController.readCSV(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(requireNonNull(response.getBody()).getData());
    }


    private String buildUrl(String contextPath) {
        String url = "http://localhost:" + port + contextPath;
        LOG.info("URL : {}", url);
        return url;
    }

    private MultipartFile multipartFile(String path) throws IOException {
        File file = new File("src/main/resources/data/files/" + path);
        FileInputStream fileInputStream = new FileInputStream(file);

        return new MockMultipartFile(file.getName(), fileInputStream);
    }
}
