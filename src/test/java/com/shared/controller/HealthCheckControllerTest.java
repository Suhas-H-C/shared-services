package com.shared.controller;

import com.shared.algo.controller.HealthCheckController;
import com.shared.algo.utils.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(MockitoExtension.class)
class HealthCheckControllerTest {

    @InjectMocks
    private HealthCheckController healthCheckController;

    @Test
    void healthCheck() {
        ResponseEntity<GenericResponse<?>> response = healthCheckController.healthCheck();
        log.info("{}", Objects.requireNonNull(response.getBody()).data());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
