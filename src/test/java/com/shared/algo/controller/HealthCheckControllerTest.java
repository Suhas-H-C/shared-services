package com.shared.algo.controller;

import com.shared.algo.utils.GenericResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HealthCheckControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(HealthCheckControllerTest.class);
    @InjectMocks
    private HealthCheckController healthCheckController;

    @Test
    @DisplayName("Health Check")
    void healthCheck() {
        ResponseEntity<GenericResponse<?>> response = healthCheckController.healthCheck();
        LOG.info("{}", Objects.requireNonNull(response.getBody()).getData());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
