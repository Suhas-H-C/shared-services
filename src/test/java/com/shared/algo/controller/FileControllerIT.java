package com.shared.algo.controller;

import com.shared.algo.utils.GenericResponse;
import com.shared.vo.TestUtils;
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
class FileControllerIT {

    private static final Logger LOG = LoggerFactory.getLogger(FileControllerIT.class);

    @LocalServerPort
    String port;

    private final FileController fileController;
    private final RestTemplate restTemplate;

    @Autowired
    public FileControllerIT(FileController fileController, RestTemplate restTemplate) {
        this.fileController = fileController;
        this.restTemplate = restTemplate;
    }

    private final TestUtils testUtils = new TestUtils();

    @Test
    void getContentIT() {
        HttpHeaders headers = testUtils
                .httpHeaders(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(testUtils.buildUrl(port, "/file/fetch-content"));
        builder.queryParam("fileName", "PDFContent.txt");

        ResponseEntity<?> response =
                restTemplate.exchange(builder.toUriString(),
                        HttpMethod.GET, httpEntity, GenericResponse.class);

        LOG.info("{}", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getJsonIT() {
        HttpHeaders headers = testUtils
                .httpHeaders(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(testUtils.buildUrl(port, "/file/fetch-json"));
        builder.queryParam("stringPath", "ipData");

        ResponseEntity<?> response =
                restTemplate.exchange(builder.toUriString(),
                        HttpMethod.GET, httpEntity, GenericResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void readCsvIT() throws IOException {
        ResponseEntity<GenericResponse<?>> response = fileController
                .readCSV(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(requireNonNull(response.getBody()).getData());
    }

    private static MultipartFile multipartFile(String path) throws IOException {
        File file = new File("src/main/resources/data/files/" + path);
        FileInputStream fileInputStream = new FileInputStream(file);

        return new MockMultipartFile(file.getName(), fileInputStream);
    }
}
