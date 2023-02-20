package com.shared.algo.controller;

import com.shared.algo.SharedServicesApplication;
import com.shared.algo.utils.GenericResponse;
import com.shared.vo.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SharedServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthCheckControllerIT {

    @LocalServerPort
    String port;

    private final TestRestTemplate testRestTemplate = new TestRestTemplate();
    private final TestUtils testUtils = new TestUtils();

    @Test
    void healthCheck() {
        HttpHeaders httpHeaders = testUtils.httpHeaders(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<?> response = testRestTemplate
                .exchange(testUtils.buildUrl(port, "/health/health-check"), HttpMethod.GET, httpEntity, GenericResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
